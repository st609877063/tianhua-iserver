package com.gift.dao;import java.util.List;import com.gift.bean.*;public interface Gift_operationDAO {    public void saveGift_operation(Gift_operation gift_operation);    public void removeGift_operation(Gift_operation gift_operation);    public Gift_operation findGift_operationByOper_id(Integer oper_id);    public List<Gift_operation> findAllGift_operation();    public void updateGift_operation(Gift_operation gift_operation);    public int findGift_operationByOper_name_count( String oper_name_c );    public List<Gift_operation> findGift_operationByOper_name( String oper_name_c , int currentPage,int lineSize);    public List<Gift_operation> findGift_operationByOper_name_excel( String oper_name_c );}
