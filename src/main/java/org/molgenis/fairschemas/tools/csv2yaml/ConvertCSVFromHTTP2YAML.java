package org.molgenis.fairschemas.tools.csv2yaml;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.opencsv.CSVReader;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.molgenis.fairschemas.tools.csv2yaml.HttpGet.httpGet;

public class ConvertCSVFromHTTP2YAML {

    public static void main(String args[]) throws Exception {
        String url = "https://raw.githubusercontent.com/molgenis/molgenis-emx2/master/data/fairdatahub/ontologies/Genes.csv";
        File yamlOut = new File("/Users/joeri/git/fair-schemas/ontologies/HumanGenes.yaml");
        ConvertCSVFromHTTP2YAML convertCSV2YAML = new ConvertCSVFromHTTP2YAML(url, yamlOut);
        convertCSV2YAML.convert();
    }

    private String url;
    private File yamlOut;

    public ConvertCSVFromHTTP2YAML(String url, File yamlOut)
    {
        this.url = url;
        this.yamlOut = yamlOut;
    }

    public void convert() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        String httpContent = httpGet(url);
        Reader reader = new StringReader(httpContent);

        CSVReader csvReader = new CSVReader(reader);
        csvReader.readNext(); // skip header
        String[] values;
        List<OntologyTerm> ontoList = new ArrayList<>();
        while ((values = csvReader.readNext()) != null) {
            ontoList.add(new OntologyTerm(values));
        }
        objectMapper.writeValue(yamlOut, ontoList);

    }
}
