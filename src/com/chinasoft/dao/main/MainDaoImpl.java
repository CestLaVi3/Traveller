package com.chinasoft.dao.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.chinasoft.dao.BaseDao;
import com.chinasoft.model.main.SysHref;
import com.chinasoft.model.main.SysMainImage;
import com.chinasoft.model.spot.SysScenicSpot;


public class MainDaoImpl extends BaseDao implements MainDao {

	public List findMainHref() {
		String sql="select * from sys_href";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysHref sysHref=new SysHref();
				sysHref.setHrefId(rs.getInt("Href_Id"));
				sysHref.setHrefName(rs.getString("Href_Name"));
				sysHref.setHrefUrl(rs.getString("Href_Url"));
				return sysHref;
			}
		});
	}

	public List findMainImage() {
		String sql="select * from sys_main_image";
		return getJdbcTemplate().query(sql, new Object[]{}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysMainImage mainImage=new SysMainImage();
				mainImage.setImageId(rs.getInt("Image_Id"));
				mainImage.setImageUrl(rs.getString("Image_Url"));
				mainImage.setImageDesc(rs.getString("Image_Desc"));
				return mainImage;
			}
		});
	}

	
	public void addImage(SysMainImage mainImage) {
		String sql2 = "insert into sys_main_image(image_id,image_url,image_desc) values(seq_user.nextval,?,?)";
		this.getJdbcTemplate().update(sql2, new Object[] {mainImage.getImageUrl(),mainImage.getImageDesc()});
	}

	
	public void updateImage(SysMainImage mainImage) {
		String sql2 = "update sys_main_image set image_url=?,image_desc=? where image_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {mainImage.getImageUrl(),mainImage.getImageDesc(),mainImage.getImageId()});
	}

	
	public SysMainImage findMainImageById(int imageId) {
		String sql="select * from sys_main_image where image_id=?";
		return (SysMainImage)getJdbcTemplate().query(sql, new Object[] {imageId},new ResultSetExtractor() {					
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					SysMainImage mainImage=new SysMainImage();
					mainImage.setImageId(rs.getInt("Image_Id"));
					mainImage.setImageUrl(rs.getString("Image_Url"));
					mainImage.setImageDesc(rs.getString("Image_Desc"));
					return mainImage;
				}
				return null;
			}
		});
	}

	
	public void deleteImage(String imageId) {
		String sql2 = "delete from sys_main_image where image_id in("+imageId+")";
		this.getJdbcTemplate().update(sql2, new Object[] {});
	}

	
	public List findMainHrefByName(String hrefName,int pageNum,int pageCount) {
		int i1=pageNum*pageCount;
		int i2=(pageNum-1)*pageCount;
		
		String sql="select aa.*,bb.coun from (select * from (select p.*,rownum t from sys_href p where p.href_name like '%"+hrefName+"%' order by href_id asc) where t<=? and t>?) aa inner join (select count(*) as coun from sys_href where href_name like '%"+hrefName+"%') bb on 1=1";
		return getJdbcTemplate().query(sql, new Object[]{i1,i2}, new RowMapper() {
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				SysHref sysHref=new SysHref();
				sysHref.setCount(rs.getInt("coun"));
				sysHref.setHrefId(rs.getInt("Href_Id"));
				sysHref.setHrefName(rs.getString("Href_Name"));
				sysHref.setHrefUrl(rs.getString("Href_Url"));
				return sysHref;
			}
		});
	}

	
	public void addMainHref(SysHref href) {
		String sql2 = "insert into sys_href(href_id,href_name,href_url) values(seq_user.nextval,?,?)";
		this.getJdbcTemplate().update(sql2, new Object[] {href.getHrefName(),href.getHrefUrl()});
	}

	
	public void updateMainHref(SysHref href) {
		String sql2 = "update sys_href set href_name=?,href_url=? where href_id=?";
		this.getJdbcTemplate().update(sql2, new Object[] {href.getHrefName(),href.getHrefUrl(),href.getHrefId()});
	}

	
	public void deleteMainHref(String hrefId) {
		String sql2 = "delete from sys_href where href_id in("+hrefId+")";
		this.getJdbcTemplate().update(sql2, new Object[] {});
	}

}
