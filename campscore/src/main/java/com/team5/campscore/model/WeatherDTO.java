package com.team5.campscore.model;


import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data
@Alias("weather")
public class WeatherDTO {
	
	public String getRcode() {
		return this.rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTp0() {
		return tp0;
	}
	public void setTp0(String tp0) {
		this.tp0 = tp0;
	}
	public String getTp1() {
		return tp1;
	}
	public void setTp1(String tp1) {
		this.tp1 = tp1;
	}
	public String getTp2() {
		return tp2;
	}
	public void setTp2(String tp2) {
		this.tp2 = tp2;
	}
	public String getTp3() {
		return tp3;
	}
	public void setTp3(String tp3) {
		this.tp3 = tp3;
	}
	public String getTp4() {
		return tp4;
	}
	public void setTp4(String tp4) {
		this.tp4 = tp4;
	}
	public String getTp5() {
		return tp5;
	}
	public void setTp5(String tp5) {
		this.tp5 = tp5;
	}
	public String getTp6() {
		return tp6;
	}
	public void setTp6(String tp6) {
		this.tp6 = tp6;
	}
	public String getTp7() {
		return tp7;
	}
	public void setTp7(String tp7) {
		this.tp7 = tp7;
	}
	public String getWc0() {
		return wc0;
	}
	public void setWc0(String wc0) {
		this.wc0 = wc0;
	}
	public String getWc1() {
		return wc1;
	}
	public void setWc1(String wc1) {
		this.wc1 = wc1;
	}
	public String getWc2() {
		return wc2;
	}
	public void setWc2(String wc2) {
		this.wc2 = wc2;
	}
	public String getWc3() {
		return wc3;
	}
	public void setWc3(String wc3) {
		this.wc3 = wc3;
	}
	public String getWc4() {
		return wc4;
	}
	public void setWc4(String wc4) {
		this.wc4 = wc4;
	}
	public String getWc5() {
		return wc5;
	}
	public void setWc5(String wc5) {
		this.wc5 = wc5;
	}
	public String getWc6() {
		return wc6;
	}
	public void setWc6(String wc6) {
		this.wc6 = wc6;
	}
	public String getWc7() {
		return wc7;
	}
	public void setWc7(String wc7) {
		this.wc7 = wc7;
	}
	public String getWcd0() {
		return wcd0;
	}
	public void setWcd0(String wcd0) {
		this.wcd0 = wcd0;
	}
	public String getWcd1() {
		return wcd1;
	}
	public void setWcd1(String wcd1) {
		this.wcd1 = wcd1;
	}
	public String getWcd2() {
		return wcd2;
	}
	public void setWcd2(String wcd2) {
		this.wcd2 = wcd2;
	}
	public String getWcd3() {
		return wcd3;
	}
	public void setWcd3(String wcd3) {
		this.wcd3 = wcd3;
	}
	public String getWcd4() {
		return wcd4;
	}
	public void setWcd4(String wcd4) {
		this.wcd4 = wcd4;
	}
	public String getWcd5() {
		return wcd5;
	}
	public void setWcd5(String wcd5) {
		this.wcd5 = wcd5;
	}
	public String getWcd6() {
		return wcd6;
	}
	public void setWcd6(String wcd6) {
		this.wcd6 = wcd6;
	}
	public String getWcd7() {
		return wcd7;
	}
	public void setWcd7(String wcd7) {
		this.wcd7 = wcd7;
	}
	public String getRp0() {
		return rp0;
	}
	public void setRp0(String rp0) {
		this.rp0 = rp0;
	}
	public String getRp1() {
		return rp1;
	}
	public void setRp1(String rp1) {
		this.rp1 = rp1;
	}
	public String getRp2() {
		return rp2;
	}
	public void setRp2(String rp2) {
		this.rp2 = rp2;
	}
	public String getRp3() {
		return rp3;
	}
	public void setRp3(String rp3) {
		this.rp3 = rp3;
	}
	public String getRp4() {
		return rp4;
	}
	public void setRp4(String rp4) {
		this.rp4 = rp4;
	}
	public String getRp5() {
		return rp5;
	}
	public void setRp5(String rp5) {
		this.rp5 = rp5;
	}
	public String getRp6() {
		return rp6;
	}
	public void setRp6(String rp6) {
		this.rp6 = rp6;
	}
	public String getRp7() {
		return rp7;
	}
	public void setRp7(String rp7) {
		this.rp7 = rp7;
	}
	private String rcode;
	private String addr;
	private String tp0;
	private String tp1;
	private String tp2;
	private String tp3;
	private String tp4;
	private String tp5;
	private String tp6;
	private String tp7;
	private String wc0;
	private String wc1;
	private String wc2;
	private String wc3;
	private String wc4;
	private String wc5;
	private String wc6;
	private String wc7;
	private String wcd0;
	private String wcd1;
	private String wcd2;
	private String wcd3;
	private String wcd4;
	private String wcd5;
	private String wcd6;
	private String wcd7;
	private String rp0;
	private String rp1;
	private String rp2;
	private String rp3;
	private String rp4;
	private String rp5;
	private String rp6;
	private String rp7;

}
