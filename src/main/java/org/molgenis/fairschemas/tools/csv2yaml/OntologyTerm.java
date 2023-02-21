package org.molgenis.fairschemas.tools.csv2yaml;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OntologyTerm {

    String name;
    String definition;
    String codesystem;
    String code;
    String ontologyTermURI;

    public OntologyTerm(String[] values) {
        this.name = values[0];
        this.definition = !values[1].equals(values[0]) ? values[1] : null;
        this.codesystem = values[2];
        this.code = values[3];
        this.ontologyTermURI = values[4];
    }

}
