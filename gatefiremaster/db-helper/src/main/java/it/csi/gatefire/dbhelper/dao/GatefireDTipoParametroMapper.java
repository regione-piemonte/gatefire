package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireDTipoParametro;
import it.csi.gatefire.dbhelper.model.GatefireDTipoParametroExample;

public interface GatefireDTipoParametroMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	long countByExample(GatefireDTipoParametroExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByExample(GatefireDTipoParametroExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByPrimaryKey(String tipo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insert(GatefireDTipoParametro record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insertSelective(GatefireDTipoParametro record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	List<GatefireDTipoParametro> selectByExample(GatefireDTipoParametroExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	GatefireDTipoParametro selectByPrimaryKey(String tipo);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExampleSelective(@Param("record") GatefireDTipoParametro record,
			@Param("example") GatefireDTipoParametroExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExample(@Param("record") GatefireDTipoParametro record,
			@Param("example") GatefireDTipoParametroExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKeySelective(GatefireDTipoParametro record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_parametro
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKey(GatefireDTipoParametro record);
}