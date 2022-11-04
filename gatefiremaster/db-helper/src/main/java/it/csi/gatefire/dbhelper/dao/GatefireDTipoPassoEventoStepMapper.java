/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import it.csi.gatefire.dbhelper.model.GatefireDTipoPassoEventoStep;
import it.csi.gatefire.dbhelper.model.GatefireDTipoPassoEventoStepExample;

public interface GatefireDTipoPassoEventoStepMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	long countByExample(GatefireDTipoPassoEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByExample(GatefireDTipoPassoEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int deleteByPrimaryKey(String codice);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insert(GatefireDTipoPassoEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int insertSelective(GatefireDTipoPassoEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	List<GatefireDTipoPassoEventoStep> selectByExample(GatefireDTipoPassoEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	GatefireDTipoPassoEventoStep selectByPrimaryKey(String codice);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExampleSelective(@Param("record") GatefireDTipoPassoEventoStep record,
			@Param("example") GatefireDTipoPassoEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByExample(@Param("record") GatefireDTipoPassoEventoStep record,
			@Param("example") GatefireDTipoPassoEventoStepExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKeySelective(GatefireDTipoPassoEventoStep record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table gatefire.gatefire_d_tipo_passo_evento_step
	 * @mbg.generated  Fri Jan 29 11:47:25 CET 2021
	 */
	int updateByPrimaryKey(GatefireDTipoPassoEventoStep record);
}