package com.artemius.dwshop.services.imps;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.entities.CartItem;
import com.artemius.dwshop.entities.MerchSize;
import com.artemius.dwshop.entities.Order;
import com.artemius.dwshop.entities.OrderStatus;
import com.artemius.dwshop.repositories.CartItemRepository;
import com.artemius.dwshop.repositories.OrderRepository;
import com.artemius.dwshop.repositories.SectionRepository;
import com.artemius.dwshop.services.AccountService;
import com.artemius.dwshop.services.EmailService;
import com.artemius.dwshop.services.PurchaseService;
import com.artemius.dwshop.services.SizeService;

@Service
public class PurService implements PurchaseService {
    @Autowired
    private OrderRepository os; //to be replaced with service;
    @Autowired
    private CartItemRepository cs; //to be replaced with service;
    @Autowired
    private SizeService ss = new MSizeService();
    @Autowired
    private AccountService ass = new AccService();
    @Autowired
    private SectionRepository ses;
    @Autowired
    private EmailService es;

    @Override
    public void purchse(Map<String, Object> model, Principal principal) {
	List<CartItem> items = cs.findAllByUsername(principal.getName());
	List<Order> orders = new ArrayList<>();
	double totalPrice=0;
	double actualPrice=0;
	boolean purchaseSuccess = true;
	String purchaseDescription = "Ваш заказ был оплачен. В ближайшее время он поступит на доставку.";
	String purchaseStatus = "Оплата заказа прошла успешно!";
	DateFormat f = new SimpleDateFormat("d MMMM YYYY");
	String cheque="<!DOCTYPE html>\r\n" + 
		"<html>\r\n" + 
		"	<head>\r\n" + 
		"		<title>Cheuqe output</title>\r\n" + 
		"		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n" + 
		"	</head>\r\n" + 
		"	<body>\r\n" + 
		"		<table style=\"font-family: Consolas; font-size: 2vh\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"500\">\r\n" + 
		"			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\">DW SHOP RUSSIA</td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\"><p>********************************************</p></td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td width=\"250\" style=\"text-align: left\"><p>Дата: "+f.format(new Date())+"<p></td>\r\n" + 
		"				<td colspan=\"3\" width=\"250\" style=\"text-align: right\"><p>Идентификатор: "+items.hashCode()+"<p></td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\"><p>********************************************</p></td>\r\n" + 
		"			</tr>"
		+ "			<tr>\r\n" + 
		"				<td width=\"200\" style=\"text-align: left\"><p>Товар: <p></td>\r\n" + 
		"				<td width=\"100\" style=\"text-align: right\"><p>Размер: <p></td>\r\n" + 
		"				<td width=\"100\" style=\"text-align: right\"><p>Кол-во: <p></td>\r\n" + 
		"				<td width=\"100\" style=\"text-align: right\"><p>Цена: <p></td>\r\n" + 
		"			</tr>";
	for (CartItem i: items) {
	    if (i.getQuantity()>i.getMerchSizeFK().getQuantity()) {
		purchaseSuccess=false;
		purchaseStatus = "Оплата заказа не удалась!";
		purchaseDescription = "Мы искренне сожалеем об этом. Вы пытаетесь заказать больше товара, чем есть на складе: "
		+i.getMerchFK().getTitle()+" присутствует на складе в нужном размере в "+i.getMerchSizeFK().getQuantity()
		+", в то время как вы пытаетесь заказать "+i.getQuantity()+" экземпляров.";
		break;
	    }
	    totalPrice+=i.getMerchFK().getPrice()*i.getQuantity();
	    actualPrice+=i.getCost();
	    Long merchPurchases = i.getMerchFK().getPurchases();
	    Long merchSizePurchases = i.getMerchSizeFK().getPurchases();
	    i.getMerchFK().setPurchases(merchPurchases+i.getQuantity());
	    i.getMerchSizeFK().setPurchases(merchSizePurchases+i.getQuantity());
	    Order o = new Order();
	    o.setItemFK(i);
	    o.setDate(f.format(new Date()));
	    o.setStatus(OrderStatus.Ожидается);	 
	    //i.setDiscarded(true);
	    orders.add(o);
	    cheque+="			<tr>\r\n" + 
	    	"				<td width=\"200\" style=\"text-align: left\"><p>"+i.getMerchFK()
	    	.getTitle()+"<p></td>\r\n" + 
	    	"				<td width=\"100\" style=\"text-align: right\"><p>"+i.getMerchSizeFK()
	    	.getSizeFK()
	    	.getTitle()+"<p></td>\r\n" + 
	    	"				<td width=\"100\" style=\"text-align: right\"><p>"+i.getQuantity()+"<p></td>\r\n" + 
	    	"				<td width=\"100\" style=\"text-align: right\"><p>"+i.getMerchFK().getPrice()*i.getQuantity()+"<p></td>\r\n" + 
	    	"			</tr>";
	    cs.removeById(i.getId_PK());
	}
	cheque+="			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\"><p>********************************************</p></td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td width=\"250\" style=\"text-align: left\"><p>Скидка: "+(totalPrice-actualPrice)+"<p></td>\r\n" + 
		"				<td colspan=\"3\" width=\"250\" style=\"text-align: right\"><p>Итого: "+actualPrice+"<p></td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\"><p>********************************************</p></td>\r\n" + 
		"			</tr>\r\n" + 
		"			<tr>\r\n" + 
		"				<td colspan=\"5\" style=\"text-align: center\">СПАСИБО ЗА ПОКУПКУ!</td>\r\n" + 
		"			</tr>"+
		"		</table>\r\n" + 
		"	</body>\r\n" + 
		"</html>";
	model.put("user",ass.findByUsername(principal.getName()));
	model.put("purchaseStatus",purchaseStatus);
	model.put("purchaseDescription",purchaseDescription);
	model.put("sections",ses.findAll());   
	if (purchaseSuccess) {
	    os.saveAll(orders);
	    es.sendHtmlEmail("Электронный чек",cheque,principal.getName());
	    for (CartItem i: items) {
		MerchSize toSave = i.getMerchSizeFK();
		long q = ss.getMerchSizeByID(toSave.getId_PK()).get().getQuantity();
		toSave.setQuantity(q-i.getQuantity());
		ss.save(toSave);
	    }
	}
    }

}
