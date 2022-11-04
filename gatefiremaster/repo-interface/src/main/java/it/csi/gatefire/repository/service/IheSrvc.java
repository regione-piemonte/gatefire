/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.service;

import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.RepositoryDocIdInput;
import it.csi.gatefire.common.model.RepositoryInput;
import it.csi.gatefire.common.model.RepositoryMetadataUpdateInput;
import it.csi.gatefire.common.model.RepositoryQueryResult;
import it.csi.gatefire.common.model.RepositoryUndoInput;
import it.csi.gatefire.common.model.Result;

public interface IheSrvc {
	Result archiviaDoc(RepositoryInput input, CallInfo callInfo, Long idEvento, Long idEventoPadre);

	FileResult recuperaDoc(RepositoryDocIdInput input, CallInfo callInfo, Long idEvento);

	public RepositoryQueryResult recuperaMetadataDoc(RepositoryDocIdInput input, CallInfo callInfo, Long idEvento);

	Result modificaMetadati(RepositoryMetadataUpdateInput input, CallInfo callInfo, Long idEvento);

	Result annullaDoc(RepositoryUndoInput input, CallInfo callInfo, Long idEvento);
}
