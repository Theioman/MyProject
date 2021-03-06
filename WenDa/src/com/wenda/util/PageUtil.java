package com.wenda.util;

import java.util.List;

import com.wenda.domain.Users;


/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageUtil {
	
	private List list;//查询结果
	private Integer pageSize;//每页显示几条数据
	private Integer page_current;//用户请求的页码
	
	
	private Integer dataSize;//数据的总条数
	private List subList;//对应请求页面的查询结果
	private Integer pageCount;//总页数
	
	/**
	 * 根据用户给定的数据进行计算获取用户最终想要的数据：
	 * 1.用户能够给定我们什么数据：
	 * 查询结果    每页显示几条数据（默认10条） 用户希望显示第几页的数据  
	 * 2.用户最终想要的数据
	 * 对应请求页面的查询结果  分页的所有页码  一共有多少条数据  当前是第几页  （还有是否有下一页，是否有上一页，首页，最后一页）
	 */
	
	public PageUtil(List list,Integer pageSize,Integer page_current)
	{
		this.list=list;
		//给每页显示条数属性进行赋值
		if(pageSize==null)
		{
			this.pageSize=5;
		}else
		{
			this.pageSize=pageSize;
		}
		//给请求页码属性进行赋值
		if(page_current==null)
		{
			this.page_current=1;
		}else
		{
			this.page_current=page_current;
		}
		
		
		//如果list集合是null，怎么办？
		if(list==null)
		{
			dataSize=null;
			subList=null;
			pageCount=null;
		}else if(list!=null&&list.size()==0)
		{
			//如果list集合不是null，但是size为0怎么办？
			dataSize=null;
			subList=null;
			pageCount=null;
		}else
		{
			//如果list集合有数据？
			//获取数据的总条数
			this.dataSize=list.size();
			
			//对pageCount属性进行加工取值
			if(this.dataSize%this.pageSize!=0)
			{
				//数据总条数除以每页显示条数不能整除怎么办？
				this.pageCount=this.dataSize/this.pageSize+1;
			}else
			{
				//数据总条数除以每页显示条数能整除怎么办？
				this.pageCount=this.dataSize/this.pageSize;
			}
			if(this.page_current>this.pageCount) {
				this.page_current=this.pageCount;
			}
			//对对应页面的数据集合属性subList进行赋值运算
			if(this.page_current==this.pageCount)//用户希望获取的是最后一页数据
			{
				//10   1:0-10  2:10-20  3:20-30  4:30-34
				this.subList=this.list.subList((this.page_current-1)*this.pageSize, list.size());
				
			}else if(this.page_current==1)//用户希望获取的是第一页数据
			{
				if(this.page_current==this.pageCount)//第一页就是最后一页的情况
				{
					this.subList=this.list.subList((this.page_current-1)*this.pageSize, list.size());
				}else
				{
					this.subList=this.list.subList(0, this.pageSize);
				}
				
			}else//用户希望获取的是中间页数据
			{
				this.subList=this.list.subList((this.page_current-1)*this.pageSize, (this.page_current)*this.pageSize);
			}
		}
		
	}
	
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Integer getDataSize() {
		return dataSize;
	}
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPage_current() {
		return page_current;
	}
	public void setPage_current(Integer page_current) {
		this.page_current = page_current;
	}
	public List getSubList() {
		return subList;
	}
	public void setSubList(List subList) {
		this.subList = subList;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	
	
	

}
