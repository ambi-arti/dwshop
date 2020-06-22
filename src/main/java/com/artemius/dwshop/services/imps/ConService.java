package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.EditedAccount;
import com.artemius.dwshop.entities.Merch;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.ConsumerService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.SizeService;

@Service
public class ConService implements ConsumerService {
    
    @Autowired
    AccountService ass = new AccService();
    @Autowired
    OrderRepository os;
    @Autowired
    CartItemRepository cs;
    @Autowired
    SizeService ss = new MSizeService();
    @Autowired
    MerchService ms = new MerService();
    @Autowired
    DiscountService ds = new DiscService();

    @Override
    public boolean account(Map<String, Object> model, EditedAccount user, Principal principal) {	
	       boolean passOnly=false;
	       if (os.findAllUndeliveredByUsername(principal.getName()).size()>0) 
		   passOnly=true;
	       Account current = ass.findByUsername(principal.getName());
	       boolean flawed = false;
	       boolean passwordChanged=false;
	       if (user.getPassword().length()>1)
		   passwordChanged=true;
	       if (!passOnly) {
		       if (ass.isDateOkay(user.getBirthdate())==false) {
			   model.put("badDate","Неверный формат даты рождения!");
			   flawed=true;
		       }
		       if (ass.isCityMatchingPattern(user.getCity())==false) {
			   model.put("wrongCity","Неверный формат названия!");
			   flawed=true;
		       }
	       }
	       
	       if (passwordChanged&&ass.isPasswordUnique(user.getPassword(),current)==false) {
		   model.put("passwordTooShort","Введите другой пароль!");
		   flawed=true;
	       }
	       if (passwordChanged&&ass.isPasswordLongEnough(user.getPassword())==false) {
		   model.put("passwordTooShort","Пароль содержит менее 8 символов!");
		   flawed=true;
	       }
	       if (passwordChanged&&!(user.getPassword().equals(user.getConfirm()))) {
		   model.put("passwordDoesntMatch","Пароли не совпадают!");
		   flawed=true;
	       }
	       if (!flawed) {
		   if (!passOnly) {
			if (user.getFirstname().length()>2) {
			   current.setFirstname(user.getFirstname());
			}
			if (user.getSurname().length()>2) {
			   current.setSurname(user.getSurname());
			}
			if (user.getPatronymic().length()>2) {
			   current.setPatronymic(user.getPatronymic());
			}
			if (user.getBirthdate().length()>2) {
			   current.setBirthdate(user.getBirthdate());
			}
			if (user.getCity().length()>2) {
			   current.setCity(user.getCity());
			}
			if (user.getAddress().length()>2) {
			   current.setAddress(user.getAddress());
			}
			if (user.getCity().length()>2) {
			   current.setCity(user.getCity());
			}
		   }

		   if (passwordChanged) {
		       current.setPassword(user.getPassword());
		   }
		   ass.saveNewAccount(current);
		   return true;
	        }
		model.put("surname",user.getSurname());
		model.put("firstname",user.getFirstname());
		model.put("patronymic",user.getPatronymic());
		model.put("city",user.getCity());
		model.put("birthdate",user.getBirthdate());
		model.put("address",user.getAddress());
	return false;
    }

    @Override
    public void account(Map<String, Object> model, Principal principal) {
      	Account user = ass.findByUsername(principal.getName());
      	if (os.findAllUndeliveredByUsername(principal.getName()).size()>0) {
  	    model.put("readOnly","В данный момент изменение личных данных невозможно: у вас есть ожидающие, или возвращаемые заказы");
  	    model.put("ro","disabled");
      	}
  	model.put("surname",user.getSurname());
  	model.put("firstname",user.getFirstname());
  	model.put("patronymic",user.getPatronymic());
  	model.put("city",user.getCity());
  	model.put("birthdate",user.getBirthdate());
  	model.put("address",user.getAddress());
    }

    @Override
    public void fillCart(Map<String, Object> model, Principal principal) {
       	List<CartItem> items = cs.findAllByConsumerFK(ass
       		.findByUsername(principal.getName()));
       	double totalCost = 0;
       	for (CartItem i: items) {
       	    totalCost+= (i.getCost()*i.getQuantity());
       	}
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);       	
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));
       	model.put("totalCost",totalCost);
    }

    @Override
    public void incrementCart(Long itemId, Map<String, Object> model, Principal principal) {
	long quantity = cs.findById(itemId).get()
		.getQuantity();
	long max = ss.getMerchSizeByID(cs.findById(itemId)
		.get().getMerchSizeFK()
		.getId_PK())
		.get().getQuantity();
	if (quantity<max) {
	   CartItem toSave = cs.findById(itemId).get();
	   toSave.setQuantity(quantity+1);
	   cs.save(toSave);
	       	List<CartItem> items = cs.findAllByConsumerFK(ass
	       		.findByUsername(principal.getName()));
	       	double totalCost = 0;
	       	for (CartItem i: items) {
	       	    totalCost+= (i.getCost()*i.getQuantity());
	       	}
	       	if (items.isEmpty())
	       	    model.put("hasItems",false);
	       	else model.put("hasItems",true);       	
	       	model.put("items",items);
	       	model.put("user",ass.findByUsername(principal.getName()));
	       	model.put("totalCost",totalCost);   
	}
    }

    @Override
    public void decrementCart(Long itemId, Map<String, Object> model, Principal principal) {
	long quantity = cs.findById(itemId).get()
		.getQuantity();
	if (quantity>0) {
	    CartItem toSave = cs.findById(itemId).get();
	    toSave.setQuantity(quantity-1);
	    cs.saveAndFlush(toSave);
	       	List<CartItem> items = cs.findAllByConsumerFK(ass
	       		.findByUsername(principal.getName()));
	       	double totalCost = 0;
	       	for (CartItem i: items) {
	       	    totalCost+= (i.getCost()*i.getQuantity());
	       	}
	       	if (items.isEmpty())
	       	    model.put("hasItems",false);
	       	else model.put("hasItems",true);       	
	       	model.put("items",items);
	       	model.put("user",ass.findByUsername(principal.getName()));
	       	model.put("totalCost",totalCost);
	}
    }

    @Override
    public void removeCart(Long itemId, Map<String, Object> model, Principal principal) {
	cs.deleteById(itemId);
       	List<CartItem> items = cs.findAllByConsumerFK(ass
       		.findByUsername(principal.getName()));
       	double totalCost = 0;
       	for (CartItem i: items) {
       	    totalCost+= (i.getCost()*i.getQuantity());
       	}
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);       	
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));
       	model.put("totalCost",totalCost);
    }

    @Override
    public String addCart(Long merchId, Long amount, Long sizeId, Map<String, Object> model, Principal principal) {
    	if (principal!=null) {
    	    if (amount>ss.getMerchSizeByID(sizeId).get().getQuantity())
    		return "Слишком мало товара на складе!";   	    
    	}
    	else return "Войдите в аккаунт!";
	    CartItem ex = cs.findByUsernameAndMerchSize(principal.getName(),sizeId);
	    if (ex!= null) {
		long init = ex.getQuantity();
		if (init+amount>ss.getMerchSizeByID(sizeId).get().getQuantity())
		    return "Слишком мало товара на складе!";
		ex.setQuantity(init+amount);
		cs.save(ex);
		return "Готово!";
	    }
	    CartItem ci = new CartItem();
	    ci.setMerchFK(ms.findById(merchId).get());
	    ci.setMerchSizeFK(ss.getMerchSizeByID(sizeId).get());
	    ci.setQuantity(amount);
	    double cost = ms.findById(merchId).get().getPrice();
	    List<MerchDiscount> discs = ds.getMerchDiscountsByMerchID(merchId);
	    List<Double> values = new ArrayList<>();
	    for (MerchDiscount md:discs) 
		values.add(md.getDiscountFK().getValue());
	    for (Double val: values)
		cost*=val;
	    ci.setCost(cost);
	    ci.setConsumerFK(ass.findByUsername(principal.getName()));
	    ci.setDiscarded(false);
	    cs.save(ci);
	    return "Готово!";
    }

    @Override
    public void orders(Map<String, Object> model, Principal principal) {
       	List<Order> items = os.findAllByUsername(principal.getName());
       	double totalCost = 0;
       	OrderStatus[] r = OrderStatus.values();
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);     
       	model.put("Status",r);
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));	
    }

    @Override
    public void ordersRemove(Long itemId, Map<String, Object> model, Principal principal) {
	List<Order> items = os.findAllByUsername(principal.getName());
	Order o = os.findById(itemId).get();
	os.delete(o);
	OrderStatus[] r = OrderStatus.values();
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);     
       	model.put("Status",r);
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));
    }

    @Override
    public void ordersRate(Long itemId, Long mark, Map<String, Object> model, Principal principal) {
	Merch o = os.findById(itemId)
		.get()
		.getItemFK()
		.getMerchFK();
	List<Order> items = os.findAllByUsername(principal.getName());
	Long initScore = o.getScore();
	Long initMarks = o.getMarks();
	o.setScore(initScore+mark);
	o.setMarks(initMarks+1);
	Order r = os.findById(itemId).get();
	r.setStatus(OrderStatus.DISCARDED);
	os.save(r);
	ms.addNewMerch(o);
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);     
       	model.put("Status",r);
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));
    }

}
