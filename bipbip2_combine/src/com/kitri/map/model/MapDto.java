package com.kitri.map.model;

import com.kitri.freeboard.model.BoardDto;

public class MapDto extends BoardDto{
	private int seq;
	private int mapseq;
	double loc1X;
	double loc1Y;
	double loc2X;
	double loc2Y;
	double sec1X;
	double sec1Y;
	double sec2X;
	double sec2Y;
	double sec3X;
	double sec3Y;
	String loc1;
	String loc2;
	String sec1;
	String sec2;
	String sec3;
	String memo;
	int mapHit;
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getMapseq() {
		return mapseq;
	}
	public void setMapseq(int mapseq) {
		this.mapseq = mapseq;
	}

	public double getLoc1X() {
		return loc1X;
	}
	public void setLoc1X(double loc1x) {
		loc1X = loc1x;
	}
	public double getLoc1Y() {
		return loc1Y;
	}
	public void setLoc1Y(double loc1y) {
		loc1Y = loc1y;
	}
	public double getLoc2X() {
		return loc2X;
	}
	public void setLoc2X(double loc2x) {
		loc2X = loc2x;
	}
	public double getLoc2Y() {
		return loc2Y;
	}
	public void setLoc2Y(double loc2y) {
		loc2Y = loc2y;
	}
	public double getSec1X() {
		return sec1X;
	}
	public void setSec1X(double sec1x) {
		sec1X = sec1x;
	}
	public double getSec1Y() {
		return sec1Y;
	}
	public void setSec1Y(double sec1y) {
		sec1Y = sec1y;
	}
	public double getSec2X() {
		return sec2X;
	}
	public void setSec2X(double sec2x) {
		sec2X = sec2x;
	}
	public double getSec2Y() {
		return sec2Y;
	}
	public void setSec2Y(double sec2y) {
		sec2Y = sec2y;
	}
	public double getSec3X() {
		return sec3X;
	}
	public void setSec3X(double sec3x) {
		sec3X = sec3x;
	}
	public double getSec3Y() {
		return sec3Y;
	}
	public void setSec3Y(double sec3y) {
		sec3Y = sec3y;
	}
	public String getLoc1() {
		return loc1;
	}
	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}
	public String getLoc2() {
		return loc2;
	}
	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}
	public String getSec1() {
		return sec1;
	}
	public void setSec1(String sec1) {
		this.sec1 = sec1;
	}
	public String getSec2() {
		return sec2;
	}
	public void setSec2(String sec2) {
		this.sec2 = sec2;
	}
	public String getSec3() {
		return sec3;
	}
	public void setSec3(String sec3) {
		this.sec3 = sec3;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getMapHit() {
		return mapHit;
	}
	public void setMapHit(int mapHit) {
		this.mapHit = mapHit;
	}
	
	

}
