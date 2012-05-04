package com.framework.support;

import java.util.List;
import javax.sql.RowSet;

public class PageSupport { 
      private int pageNo;
      private int pageSize;
      private int pageTotal; 
      private int recTotal; 
      private List list;
      private RowSet rowSet;
      
      public PageSupport() { 
			
			}  
      /**
		  *返回当前页码
		  */     
			public int getPageNo() { 
         return this.pageNo; 
      } 

      public void setPageNo(int pageNo) { 
         this.pageNo = pageNo; 
      }  
			/**
		  *返回每页显示的记录数
		  */  
			public int getPageSize() { 
         return this.pageSize; 
      } 

      public void setPageSize(int pageSize) { 
         this.pageSize = pageSize; 
      }
      /**
		  *返回总记录数
		  */        
      public int getRecTotal() { 
         return this.recTotal; 
      } 

      public void setRecTotal(int recTotal) { 
         this.recTotal = recTotal; 
      }           
      /**
		  *返回总页数
		  */  
      public int getPageTotal() { 
         return (this.recTotal - 1) / this.pageSize + 1;
      } 
			/**
		  *返回包含数据的结果集
		  */  
      public List getList() { 
          return list; 
      } 

      public void setList(List list) { 
          this.list = list; 
      }
      /**
		  *以rowset的形式返回包含数据的结果集
		  */
      public RowSet getRowSet() { 
          return rowSet; 
      } 

      public void setRowSet(RowSet rowSet) { 
          this.rowSet = rowSet; 
      }  
    
}