/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireDMessaggio;
import it.csi.gatefire.dbhelper.model.GatefireDMessaggioExample;

public interface GatefireDMessaggioMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	long countByExample(GatefireDMessaggioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByExample(GatefireDMessaggioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insert(GatefireDMessaggio record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insertSelective(GatefireDMessaggio record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	List<GatefireDMessaggio> selectByExample(GatefireDMessaggioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	GatefireDMessaggio selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExampleSelective(@Param("record") GatefireDMessaggio record,
			@Param("example") GatefireDMessaggioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExample(@Param("record") GatefireDMessaggio record,
			@Param("example") GatefireDMessaggioExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKeySelective(GatefireDMessaggio record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_messaggio
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKey(GatefireDMessaggio record);
}