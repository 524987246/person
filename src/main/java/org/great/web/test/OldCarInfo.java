package org.great.web.test;

import java.io.Serializable;

public class OldCarInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3370275377277358320L;
	private String carId;
	private String carMark;
	private String carStatus;
	private String companyId;

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getCarMark() {
		return carMark;
	}

	public void setCarMark(String carMark) {
		this.carMark = carMark;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}
