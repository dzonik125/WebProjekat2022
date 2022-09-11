package controller;

import java.io.IOException;
import java.util.List;

import model.PromoCode;
import service.PromoCodeService;

public class PromoCodeController {
	
	private PromoCodeService pcs = new PromoCodeService();
	
	public boolean addPromoCode(PromoCode promoCode) throws IOException {
		return pcs.addPromoCode(promoCode);
	}
	
	public List<PromoCode> findAllPromoCodes() throws IOException {
		return pcs.findAllPromoCodes();
	}

}
