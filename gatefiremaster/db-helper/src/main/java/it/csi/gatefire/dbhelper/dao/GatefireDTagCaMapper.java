package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireDTagCa;
import it.csi.gatefire.dbhelper.model.GatefireDTagCaExample;

public interface GatefireDTagCaMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	long countByExample(GatefireDTagCaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByExample(GatefireDTagCaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByPrimaryKey(String nome);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insert(GatefireDTagCa record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insertSelective(GatefireDTagCa record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	List<GatefireDTagCa> selectByExample(GatefireDTagCaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	GatefireDTagCa selectByPrimaryKey(String nome);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExampleSelective(@Param("record") GatefireDTagCa record,
			@Param("example") GatefireDTagCaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExample(@Param("record") GatefireDTagCa record, @Param("example") GatefireDTagCaExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKeySelective(GatefireDTagCa record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tag_ca
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKey(GatefireDTagCa record);
}