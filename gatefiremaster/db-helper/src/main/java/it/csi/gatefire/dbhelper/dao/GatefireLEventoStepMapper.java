/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireLEventoStep;
import it.csi.gatefire.dbhelper.model.GatefireLEventoStepExample;
import it.csi.gatefire.dbhelper.model.GatefireLEventoStepKey;

public interface GatefireLEventoStepMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	long countByExample(GatefireLEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int deleteByExample(GatefireLEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int deleteByPrimaryKey(GatefireLEventoStepKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int insert(GatefireLEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int insertSelective(GatefireLEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	List<GatefireLEventoStep> selectByExample(GatefireLEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	GatefireLEventoStep selectByPrimaryKey(GatefireLEventoStepKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int updateByExampleSelective(@Param("record") GatefireLEventoStep record,
			@Param("example") GatefireLEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int updateByExample(@Param("record") GatefireLEventoStep record,
			@Param("example") GatefireLEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int updateByPrimaryKeySelective(GatefireLEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_l_evento_step
	 * @mbg.generated  Wed Nov 25 10:50:37 CET 2020
	 */
	int updateByPrimaryKey(GatefireLEventoStep record);
}