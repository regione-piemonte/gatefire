/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativo;
import it.csi.gatefire.dbhelper.model.GatefireTParametroOperativoExample;

public interface GatefireTParametroOperativoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	long countByExample(GatefireTParametroOperativoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int deleteByExample(GatefireTParametroOperativoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int insert(GatefireTParametroOperativo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int insertSelective(GatefireTParametroOperativo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	List<GatefireTParametroOperativo> selectByExample(GatefireTParametroOperativoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	GatefireTParametroOperativo selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int updateByExampleSelective(@Param("record") GatefireTParametroOperativo record,
			@Param("example") GatefireTParametroOperativoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int updateByExample(@Param("record") GatefireTParametroOperativo record,
			@Param("example") GatefireTParametroOperativoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int updateByPrimaryKeySelective(GatefireTParametroOperativo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_t_parametro_operativo
	 * @mbg.generated  Fri Jan 29 11:57:45 CET 2021
	 */
	int updateByPrimaryKey(GatefireTParametroOperativo record);
}