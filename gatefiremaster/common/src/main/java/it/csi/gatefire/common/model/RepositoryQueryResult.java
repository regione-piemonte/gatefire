package it.csi.gatefire.common.model;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.SubmissionSet;

import it.csi.gatefire.common.model.repository.DocumentEntry;

public class RepositoryQueryResult extends BaseResult {
	DocumentEntry documentEntry;

	SubmissionSet submissionSet;

	public DocumentEntry getDocumentEntry() {
		return documentEntry;
	}

	public void setDocumentEntry(DocumentEntry documentEntry) {
		this.documentEntry = documentEntry;
	}

	public SubmissionSet getSubmissionSet() {
		return submissionSet;
	}

	public void setSubmissionSet(SubmissionSet submissionSet) {
		this.submissionSet = submissionSet;
	}

}
