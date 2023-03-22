keep in mind: DRY (don't repeat yourself)
KISS (keep it simple, stupid!)

there are 3 base 
- __archetypes__
  - archetypes are collections of attributes that makes sense
    - for instance generic archetypes like "Thing" with attributes "Identifier"
    - but also "Subject" with attributes "Age", "Gender at birth", etc
    - and "Individual" with "Ancestry", "Country of residence", "Contact address", "Gender identity"
    - and "Patient" with "Disease causal genes", "Diseases [ref_array]"
    - and "Diseases" with "Disease code, "Age of onset", "Severity", "Stage" etc
    - references between archetypes are also attributes can can choose or ignore
    - if you include them, they MUST refer to a table that implements AT LEAST that archetype
  - archetypes make NO claims or assumptions on how they should or can be combined
    - to make a 'Patient', you probably want to combine "Thing", "Subject", "Individual", "Patient" into 1 table
    - not all attributes of an archetype have to be selected, cherry-picking 1 is ok
    - but that is up to you
- __standards__
  - standards are specific sets of archetypes/attribute combined into specific tables (i.e. names)
  - 'standards' are baked into the archetypes-attribute definitions using partOfStandard
  - 'standards' only have small extra yaml for metadata
- __profiles__ can conform to one or more standards + reuse stuff + add new stuff + metadata
  - xx

