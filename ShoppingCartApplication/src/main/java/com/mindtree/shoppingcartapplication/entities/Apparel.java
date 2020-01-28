package com.mindtree.shoppingcartapplication.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "apparel")
public class Apparel extends Product {
	
	@Column(name = "apparel_type")
	private String appareltype;
	
	@Column(name = "apparel_brand")
	private String apparelbrand;
	
	@Column(name = "apparel_design")
	private String appareldesign;

	public Apparel() {
		super();
	}

	/*public Apparel(Integer prodId, String prodName, float prodPrice) {
		super(prodId, prodName, prodPrice);
	}
*/
	public Apparel(Integer prodId, String prodName, float prodPrice, String appareltype, String apparelbrand, String appareldesign) {
		super(prodId, prodName, prodPrice);
		this.appareltype = appareltype;
		this.apparelbrand = apparelbrand;
		this.appareldesign = appareldesign;
	}

	public String getAppareltype() {
		return appareltype;
	}

	public void setAppareltype(String appareltype) {
		this.appareltype = appareltype;
	}

	public String getApparelbrand() {
		return apparelbrand;
	}

	public void setApparelbrand(String apparelbrand) {
		this.apparelbrand = apparelbrand;
	}

	public String getAppareldesign() {
		return appareldesign;
	}

	public void setAppareldesign(String appareldesign) {
		this.appareldesign = appareldesign;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appareltype == null) ? 0 : appareltype.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparel other = (Apparel) obj;
		if (appareltype == null) {
			if (other.appareltype != null)
				return false;
		} else if (!appareltype.equals(other.appareltype))
			return false;
		return true;
	}

	
}
