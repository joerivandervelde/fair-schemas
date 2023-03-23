# fair-schemas
# Still very much in flux! Living design doc @ https://docs.google.com/presentation/d/1O86f7NJAUh_3Xm7q-ijQ_vrF70ltypGI5euuVFo8GrM
FAIR schemas to enable and encourage data interoperability across systems.
This repository contains best-practice _tables_ that are reused for specific projects or applications via _profiles_.
The aim is to be specific enough to be relatable and useful, but not be unnecessarily specific.
For instance, _Subject_ is reusable cross-species by replacing human-biased underlying ontologies.

## Rules for tables
* Tables represent concept archetype such as _Study_, _Biobank_, _Subject_, _Biosample_, and _Cohort_.
* Tables are supersets of reusable columns that belong to that concept.
* Identical or similar columns should ideally be merged.
* Columns can have a _partOfStandard_ attribute to indicate they represent an accepted standard. Standard must their own profile with this name.
* Columns that represent the same concept (e.g. Age) expressed in a different value type (e.g. age in years vs. age range categorical) are not explicitly connected, but should be tagged with the same semantics.
* Column names are only required to be unique within context of their table.
* Table and column names must start with letter, followed by letter, number, whitespace or underscore ([a-zA-Z][a-zA-Z0-9_ ]*).
* Things not supported:
  * Inheritance because it is too limiting. How to solve use cases for inheritance?
    * Instead of querying for an inheritance subtree you can indicate what profile of a table you want to have in your reference
    * When using refLabel for a reference the designer of an instance should ensure all columns exist in the refered to profiles.

## Rules for profiles
* Profiles represent specific projects or applications.
* Profiles can cherry-pick a combination of:
  * Tables (all columns of that table). We sometimes refer to such table instance as 'flavor'. E.g. Patient is a flavor of Subject.
  * Columns (some columns of that table)
  * Profiles (all columns included or defined by that profile)
  * Standards (all columns included or defined by that standard)
* Reused columns are chosen by referencing only their name.
* Reused columns are placed in their original table structure.
* Reused columns cannot be altered for interoperability purposes. This includes relabeling. If relabeling is required, this should be done via runtime internationalization.
* Profiles for particular applications can introduce highly specific, non-reusable tables and columns.
* To add new columns to an existing table, that table should be represented in the profile using only the name.
* New columns in new tables should be fully specified as expected from tables.

## Rules for standards
* Standards are only comprised of columns annotated with the _partOfStandard_ attribute.
* Standard names that match the column attribute _partOfStandard_ describe that standard.
* Standard cannot point to additional columns, tables or profiles.
* Standards cannot introduce additional columns.

## Syntax
### Table attributes <a id='tables'></a>
| Attribute           | Description                                                                      |
|---------------------|----------------------------------------------------------------------------------|
| name                | Name of this table. Required.                                                    |
| definedBy           | The location of the ontology term that defines this column.                      |
| definedAs           | The column definition according to the ontology term.                            |
| [columns](#columns) | The columns contained in this table, comparable to class attributes or features. |

### Column attributes <a id='columns'></a>
| Attribute      | Description                                                 |
|:---------------|-------------------------------------------------------------|
| name           | Name of this column. Required.                              |
| definedBy      | The location of the ontology term that defines this column. |
| definedAs      | The column definition according to the ontology term.       |
| dataType       | Data type of this column. Required.                         |
| unit           | Ontology term to denote the unit of measurement.            |
| partOfStandard | Mark this column is part of an accepted standard.           |
| example        | An example value to guide users.                            |

### Profile attributes <a id='profiles'></a>
| Attribute                     | Description                                      |
|-------------------------------|--------------------------------------------------|
| name                          | Name of this profile. Required.                  |
| description                   | Definition of this profile.                      |
| [authors](#authors)           | A list of contributing profile authors.          |
| [copyright](#copyright)       | A copyright statement about the profile.         |
| [license](#license)           | The license under which the profile is released. |
| [reuseColumns](#reusecolumns) | Existing columns reused by this profile.         |
| [customColumns](#tables)      | Tables or columns introduced by this profile.    |

### Standard attributes <a id='standards'></a>
| Attribute               | Description                                                   |
|-------------------------|---------------------------------------------------------------|
| name                    | Name of this profile. Required.                               |
| description             | Definition of this profile, usually adapted from an ontology. |
| [authors](#authors)     | A list of contributing profile authors.                       |
| [copyright](#copyright) | A copyright statement about the profile.                      |
| [license](#license)     | The license under which the profile is released.              |
| url                     | Link to a web address where more information can be found.    |

## Reuse columns <a id='reusecolumns'></a>
| Attribute | Description                                          |
|-----------|------------------------------------------------------|
| name      | Profile or table name, table.columnName for columns. |
| type      | "profile", "standard", "table", or "column"          |

## Author attributes <a id='authors'></a>
| Attribute | Description                                 |
|-----------|---------------------------------------------|
| name      | Name of this author.                        |
| email     | Email address of this author.               |
| orcid     | [ORCID](https://orcid.org/) of this author. |

## Copyright attributes <a id='copyright'></a>
| Attribute | Description                              |
|-----------|------------------------------------------|
| holder    | Name of the copyright holder.            |
| years     | Year of publication and latest revision. |

## License attributes <a id='license'></a>
| Attribute | Description                     |
|-----------|---------------------------------|
| name      | Name of the active license.     |
| url       | URL where license can be found. |

## Design principles for the syntax
* we prefer explicit definitions over magic. E.g. if you are ducktyping (e.g. using different flavors of a table) then the standard should make exlpicit if you assume particular columns to be present (e.g. via refLabel you can indidate what columns you expect in a lookup).
* semantic data types.