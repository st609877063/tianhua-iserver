package com.gift.dao;import java.util.List;import com.gift.bean.*;public interface Gift_logDAO {    public void saveGift_log(Gift_log gift_log);    public void removeGift_log(Gift_log gift_log);    public Gift_log findGift_logByLog_id(Integer log_id);    public List<Gift_log> findAllGift_log();    public void updateGift_log(Gift_log gift_log);    public int findGift_logByLog_content_count( String log_content_c );    public List<Gift_log> findGift_logByLog_content( String log_content_c , int currentPage,int lineSize);    public List<Gift_log> findGift_logByLog_content_excel( String log_content_c );}
