package org.molgenis.fairschemas.tools.csv2yaml;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertCSV2YAML {

    public static void main(String args[]) throws IOException, CsvValidationException {
        File csvIn = new File("/Users/joeri/git/molgenis-emx2/data/fairdatahub/ontologies/AgeGroups.csv");
        File yamlOut = new File("/Users/joeri/git/fair-schemas/lookups/AgeGroups.yaml");
        ConvertCSV2YAML convertCSV2YAML = new ConvertCSV2YAML(csvIn, yamlOut);
        convertCSV2YAML.convert();
    }

    private File csvIn;
    private File yamlOut;

    public ConvertCSV2YAML(File csvIn, File yamlOut)
    {
        this.csvIn = csvIn;
        this.yamlOut = yamlOut;
    }

    public void convert() throws IOException, CsvValidationException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        CSVReader csvReader = new CSVReader(new FileReader(this.csvIn));
        csvReader.readNext(); // skip header
        String[] values;
        List<OntologyTerm> ontoList = new ArrayList<>();
        while ((values = csvReader.readNext()) != null) {
            ontoList.add(new OntologyTerm(values));
        }
        objectMapper.writeValue(yamlOut, ontoList);
    }
}
