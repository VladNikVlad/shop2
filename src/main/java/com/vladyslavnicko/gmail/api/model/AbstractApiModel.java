package com.vladyslavnicko.gmail.api.model;


public class AbstractApiModel {

	protected Object checkOnNull(Object o) {
		if (o == null) {
			return null;
		}
		return o;
	}
}
