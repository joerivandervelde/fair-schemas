# fair-schemas
FAIR schemas to enable and encourage data interoperability across systems.
This repository contains best-practice _tables_ that are reused for specific projects or applications via _profiles_. 

## Rules for tables
* Tables represent concept archetype such as _Study_, _Biobank_, _Subject_, _Biosample_, and _Cohort_.
* Tables are supersets of reusable columns that belong to that concept.
* Identical or similar columns should ideally be merged.
* Columns can have a _partOfStandard_ attribute to indicate they represent an accepted standard. Standard must their own profile with this name.
* Columns that represent the same concept (e.g. Age) expressed in a different value type (e.g. age in years vs. age range categorical) are not explicitly connected, but should be tagged with the same semantics.
* Column names are only required to be unique within context of their table.
* Table and column names must start with letter, followed by letter, number, whitespace or underscore ([a-zA-Z][a-zA-Z0-9_ ]*).

## Rules for profiles
* Profiles represent specific projects or applications.
* Profiles can cherry-pick a combination of:
  * Tables (all columns of that table)
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
| definition          | Definition of this table, usually adapted from an ontology.                      |
| tags                | Ontology terms that best describe this table.                                    |
| [columns](#columns) | The columns contained in this table, comparable to class attributes or features. |

### Profile attributes <a id='profiles'></a>
| Attribute                     | Description                                                   |
|-------------------------------|---------------------------------------------------------------|
| name                          | Name of this profile. Required.                               |
| definition                    | Definition of this profile, usually adapted from an ontology. |
| tags                          | Ontology terms that best describe this profile.               |
| [authors](#authors)           | A list of contributing profile authors.                       |
| [copyright](#copyright)       | A copyright statement about the profile.                      |
| [license](#license)           | The license under which the profile is released.              |
| [reuseColumns](#reusecolumns) | Existing columns reused by this profile.                      |
| [newTablesOrColumns](#tables) | Tables or columns introduced by this profile.                 |

### Standard attributes <a id='standards'></a>
| Attribute               | Description                                                   |
|-------------------------|---------------------------------------------------------------|
| name                    | Name of this profile. Required.                               |
| definition              | Definition of this profile, usually adapted from an ontology. |
| tags                    | Ontology terms that best describe this profile.               |
| [authors](#authors)     | A list of contributing profile authors.                       |
| [copyright](#copyright) | A copyright statement about the profile.                      |
| [license](#license)     | The license under which the profile is released.              |
| url                     | Link to a web address where more information can be found.    |

### Column attributes <a id='columns'></a>
| Attribute      | Description                                                  |
|:---------------|--------------------------------------------------------------|
| name           | Name of this column. Required.                               |
| definition     | Definition of this column, usually adapted from an ontology. |
| tags           | Ontology terms that best describe this column.               |
| dataType       | Data type of this column. Required.                          |
| unit           | Ontology term to denote the unit of measurement.             |
| partOfStandard | Mark this column is part of an accepted standard.            |

## Reuse columns <a id='reusecolumns'></a>
| Attribute | Description                                          |
|-----------|------------------------------------------------------|
| type      | "Profile", "Table", or "Column"                      |
| name      | Profile or table name, table.columnName for columns. |

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
