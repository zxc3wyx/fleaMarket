package cn.bnt.shoppingcart.beans;

import java.util.List;

public class PageInfo {

    private int pageNum;//��ǰҳ
    private int pageSize;//ÿҳ��ʾ�ļ�¼��
    private int totalPages;
    private List<Product> list;//��ʾÿҳ������

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
