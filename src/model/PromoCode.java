package model;

import java.io.IOException;
import java.util.Date;

import repository.PromoCodeRepository;

public class PromoCode {
	private int id;
	private Date expire;
	private String code;
	private double discount;
	private int canBeUsed;
	private transient PromoCodeRepository pcr;
	
	public PromoCode(Date expire, String code, double discount, int canBeUsed) throws IOException {
		super();
		pcr = new PromoCodeRepository();
		if (pcr.findAllPromoCodes() == null) {
			this.id = 1;
		} else {
			this.id = pcr.findAllPromoCodes().get(pcr.findAllPromoCodes().size() - 1).getId() + 1;
		}
		this.expire = expire;
		this.code = code;
		this.discount = discount;
		this.canBeUsed = canBeUsed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getCanBeUsed() {
		return canBeUsed;
	}
	public void setCanBeUsed(int canBeUsed) {
		this.canBeUsed = canBeUsed;
	}
	
	
}
