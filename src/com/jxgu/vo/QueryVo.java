/**
 * 描述：
 * Copyright：Copyright （c） 2017
 * Company：南京银行
 * @author jxgu 
 * @version 1.0 2017年10月2日
 * @see history
 * 2017年10月2日
 */
package com.jxgu.vo;

import java.util.List;

import com.jxgu.pojo.Items;

/**
 * @author jxgu
 *
 *         类功能描述：
 *
 */
public class QueryVo {

    private Items items;

    // 批量删除记录
    private Integer[] ids;

    private List<Items> itemsList;

    /**
     * @return the items
     */
    public Items getItems() {
	return items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(Items items) {
	this.items = items;
    }

    /**
     * @return the ids
     */
    public Integer[] getIds() {
	return ids;
    }

    /**
     * @param ids
     *            the ids to set
     */
    public void setIds(Integer[] ids) {
	this.ids = ids;
    }

    /**
     * @return the itemsList
     */
    public List<Items> getItemsList() {
	return itemsList;
    }

    /**
     * @param itemsList
     *            the itemsList to set
     */
    public void setItemsList(List<Items> itemsList) {
	this.itemsList = itemsList;
    }

}
