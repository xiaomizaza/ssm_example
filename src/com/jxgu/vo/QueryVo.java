/**
 * ������
 * Copyright��Copyright ��c�� 2017
 * Company���Ͼ�����
 * @author jxgu 
 * @version 1.0 2017��10��2��
 * @see history
 * 2017��10��2��
 */
package com.jxgu.vo;

import java.util.List;

import com.jxgu.pojo.Items;

/**
 * @author jxgu
 *
 *         �๦��������
 *
 */
public class QueryVo {

    private Items items;

    // ����ɾ����¼
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
