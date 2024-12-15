package com.gestion.empleados.utils.pagination;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPages;
    private int numElementsForPage;
    private int currentPage;
    private List<PageItem> pages;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        numElementsForPage = 5;
        totalPages = page.getTotalPages();
        currentPage = page.getNumber() + 1;

        int from, to;
        if(totalPages <= numElementsForPage) {
            from = 1;
            to = totalPages;
        } else {
            if(currentPage <= numElementsForPage/2) {
                from = 1;
                to = numElementsForPage;
            } else if(currentPage >= totalPages - numElementsForPage/2) {
                from = totalPages - numElementsForPage + 1;
                to = numElementsForPage;
            } else {
                from = currentPage - numElementsForPage/2;
                to = numElementsForPage;
            }
        }

        for(int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, currentPage == from + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public void setPages(List<PageItem> pages) {
        this.pages = pages;
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
