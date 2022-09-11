package service;

import java.io.IOException;
import java.util.List;

import model.PromoCode;
import repository.PromoCodeRepository;

public class PromoCodeService {

	private PromoCodeRepository pcr = new PromoCodeRepository();
	
	public boolean addPromoCode(PromoCode promoCode) throws IOException {
		return pcr.addPromoCode(promoCode);
	}
	
	public List<PromoCode> findAllPromoCodes() throws IOException {
		return pcr.findAllPromoCodes();
	}
}
