
package it.csi.gatefire.repository.xds.transform.ebxml;

import ca.uhn.hl7v2.parser.PipeParser;
import org.apache.commons.lang3.StringUtils;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.PatientInfo;

import java.util.*;

/**
 * Transformation logic for a {@link PatientInfo}.
 * <p>
 * This class offers transformation between {@link PatientInfo} and an HL7v2.5
 * PID string list.
 * @author Jens Riemschneider
 */
public class PatientInfoTransformer {

    /**
     * Creates a {@link PatientInfo} instance via an HL7 XCN string.
     * @param hl7PID
     *          the HL7 PID strings. Can be <code>null</code>.
     * @return the created {@link PatientInfo} instance. <code>null</code> if no relevant 
     *          data was found in the HL7 string or the input was <code>null</code>.
     */
    public PatientInfo fromHL7(List<String> hl7PID) {
        if (hl7PID == null || hl7PID.isEmpty()) {
            return null;
        }

        var patientInfo = new PatientInfo();
        
        for (var hl7PIDLine : hl7PID) {
            var fields = PipeParser.split(hl7PIDLine.trim(), "|");
            if (fields.length == 2) {
                var iterator = patientInfo.getHl7FieldIterator(fields[0]);
                while (iterator.hasNext()) {
                    iterator.next();
                }
                var strings = PipeParser.split(fields[1], "~");
                for (var string : strings) {
                    iterator.add(string);
                }
            }
        }
        
        return patientInfo;
    }
    
    
    public it.csi.gatefire.common.model.repository.PatientInfo fromHL7ext(List<String> hl7PID) {
        if (hl7PID == null || hl7PID.isEmpty()) {
            return null;
        }

        var patientInfo = new it.csi.gatefire.common.model.repository.PatientInfo();
        
        for (var hl7PIDLine : hl7PID) {
            var fields = PipeParser.split(hl7PIDLine.trim(), "|");
            if (fields.length == 2) {
                var iterator = patientInfo.getHl7FieldIterator(fields[0]);
                while (iterator.hasNext()) {
                    iterator.next();
                }
                var strings = PipeParser.split(fields[1], "~");
                for (var string : strings) {
                    iterator.add(string);
                }
            }
        }
        
        return patientInfo;
    }
    
    /**
     * Transforms a {@link PatientInfo} instance into the HL7 representation. 
     * @param patientInfo
     *          the patient info to transform. Can be <code>null</code>.
     * @return the HL7 representation. <code>null</code> if the input was <code>null</code> 
     *          or the resulting HL7 string would be empty.
     */
    public List<String> toHL7(it.csi.gatefire.common.model.repository.PatientInfo patientInfo) {
        if (patientInfo == null) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();

        patientInfo.getAllFieldIds().stream().sorted(new PatientInfo.Hl7FieldIdComparator()).forEach(fieldId -> {
            var iterator = patientInfo.getHl7FieldIterator(fieldId);
            
            while (iterator.hasNext()) {
            	var prefix = fieldId + '|';
                var sb = new StringBuilder(prefix);
                var repetition = StringUtils.trimToEmpty(iterator.next());
                if ((repetition.length() > 255 - sb.length()) && (sb.length() > prefix.length())) {
                    sb.setLength(sb.length() - 1);
                    result.add(sb.toString());
                    sb.setLength(prefix.length());
                }
                sb.append(repetition).append('~');
                if (sb.length() > prefix.length() + 1) {
                    sb.setLength(sb.length() - 1);
                    result.add(sb.toString());
                }
            }
           
        });

        return result;
    }

}
