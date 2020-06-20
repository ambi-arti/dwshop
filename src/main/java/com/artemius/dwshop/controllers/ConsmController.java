package com.artemius.dwshop.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.EditedAccount;
import com.artemius.dwshop.entities.Account;
import com.artemius.dwshop.entities.MerchDiscount;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.Role;
import com.artemius.dwshop.entities.Roles;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.MerchSizeRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.DiscountService;
import com.artemius.dwshop.services.MerchService;
import com.artemius.dwshop.services.SizeService;
import com.artemius.dwshop.services.imps.AccService;
import com.artemius.dwshop.services.imps.DiscService;
import com.artemius.dwshop.services.imps.MSizeService;
import com.artemius.dwshop.services.imps.MerService;

@Controller
public class ConsmController {
    
    @Autowired
    MerchService ms = new MerService();
    @Autowired
    AccountService ass = new AccService();
    @Autowired
    DiscountService ds = new DiscService();
    @Autowired
    SizeService ss = new MSizeService();
    @Autowired
    CartItemRepository cs;   //to be replaced with service
    @Autowired
    MerchSizeRepository mss;
    @Autowired
    OrderRepository os;
    
   @GetMapping("/account")
   public String account(Map<String,Object> model, Principal principal) {
      	if (principal==null)
       	    return "redirect:/login";
      	Account user = ass.findByUsername(principal.getName());
      	if (os.findAllUndeliveredByUsername(principal.getName()).size()>0) {
  	    model.put("readOnly","В данный момент изменение личных данных невозможно: у вас есть ожидающие, или возвращаемые заказы");
  	    model.put("ro","disabled");
      	}
      //	model.put("ro",(os.findAllUndeliveredByUsername(principal.getName()).size()));
  	model.put("surname",user.getSurname());
  	model.put("firstname",user.getFirstname());
  	model.put("patronymic",user.getPatronymic());
  	model.put("city",user.getCity());
  	model.put("birthdate",user.getBirthdate());
  	model.put("address",user.getAddress());
      	return "account";
   }
   
   @PostMapping("/account")
   @Secured({"CONSUUMER"})
   public String account(Map<String,Object> model, EditedAccount user, Principal principal) {
       boolean passOnly=false;
       if (os.findAllUndeliveredByUsername(principal.getName()).size()>0) 
	   passOnly=true;
       Account current = ass.findByUsername(principal.getName());
       boolean flawed = false;
       boolean passwordChanged=false;
       if (user.getPassword().length()>1)
	   passwordChanged=true;
       if (ass.isCityMatchingPattern(user.getCity())==false) {
	   model.put("wrongCity","Неверный формат названия!");
	   flawed=true;
       }
       if (ass.isDateOkay(user.getBirthdate())==false) {
	   model.put("badDate","Неверный формат даты рождения!");
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
	   return "redirect:/index";
        }
	model.put("surname",user.getSurname());
	model.put("firstname",user.getFirstname());
	model.put("patronymic",user.getPatronymic());
	model.put("city",user.getCity());
	model.put("birthdate",user.getBirthdate());
	model.put("address",user.getAddress());
	return "/account";
   }
    
   @GetMapping("/cart")
    public String cart(Map<String,Object> model, Principal principal) { //redirects to the cart page
       	if (principal==null)
       	    return "redirect:/login";
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
	return "cart";
    }
   
   @PostMapping("/cart_increment")
   public String incrementCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
      	    return "redirect:/login";
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
	}
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
       	model.put("totalCost",totalCost);    
	return "cartcontents";
   }
   
   @PostMapping("/cart_decrement") 
   public String decrementCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) throws Exception{
	if (principal==null)
      	    return "redirect:/login";
	long quantity = cs.findById(itemId).get()
		.getQuantity();
	if (quantity>0) {
	    CartItem toSave = cs.findById(itemId).get();
	    toSave.setQuantity(quantity-1);
	    cs.saveAndFlush(toSave);
	}
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
       	model.put("totalCost",totalCost);   
	return "cartcontents";
   }
   
    @PostMapping("/cart_remove")
    public String removeFromCart(@RequestParam(name="itemId",required=true)Long itemId,
	    Map<String,Object> model,
	    Principal principal) {
	if (principal==null)
       	    return "redirect:/login";
	cs.removeById(itemId);
       	List<CartItem> items = cs.findAllByConsumerFK(ass
       		.findByUsername(principal.getName()));
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true); 
       	model.put("items",items);
	return "cartcontents";
    }
    
    @GetMapping("/test")
    public String testing(Map<String,Object> model) {
	model.put("list",mss.findAll());
	return "test";
    }
    
    @PostMapping("/cart_add") //adding some merch
    @ResponseBody
    @Transactional
    public String addToCart(/*@RequestParam(name="action",required=true)String action,*/
    	    @RequestParam(name="id",required=true) Long merchId,
	    @RequestParam(name="amount",required=true) Long amount,
	    @RequestParam(name="size",required=true) Long sizeId,
	    Map<String,Object> model,
	    Principal principal) {
    	if (principal!=null) {
    	    if (amount>ss.getMerchSizeByID(sizeId).get().getQuantity())
    		return "Слишком мало товара на складе!";
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
    	return "Войдите в аккаунт!";
    }
    
    @GetMapping("/orders")
    public String orders(Map<String,Object> model, Principal principal) { //redirects to the cart page
       	if (principal==null)
       	    return "redirect:/login";
       	List<Order> items = os.findAllByUsername(principal.getName());
       	double totalCost = 0;
       	if (items.isEmpty())
       	    model.put("hasItems",false);
       	else model.put("hasItems",true);       	
       	model.put("items",items);
       	model.put("user",ass.findByUsername(principal.getName()));
	return "orders";
    }
    
  /* @PostMapping("/cart") //removing cart items by item id
   public String removeFromCart(@RequestParam(name="action",required=true)String action,
	   @RequestParam(name="cartItem",required=true) Long itemId) {
       return "";
   }*/

}
