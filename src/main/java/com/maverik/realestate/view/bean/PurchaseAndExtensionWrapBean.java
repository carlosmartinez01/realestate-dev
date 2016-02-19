/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

/**
 * @author jorge
 *
 */
public class PurchaseAndExtensionWrapBean {

    private PropertyPurchaseBean purchase;

    private List<PurchaseExtensionBean> extensions;

    public PropertyPurchaseBean getPurchase() {
	return purchase;
    }

    public void setPurchase(PropertyPurchaseBean purchase) {
	this.purchase = purchase;
    }

    public List<PurchaseExtensionBean> getExtensions() {
	return extensions;
    }

    public void setExtensions(List<PurchaseExtensionBean> extensions) {
	this.extensions = extensions;
    }

}
