# Code Exercise 106

## Assumptions Taken

- **JDK 17** - Since it was not mentioned explicitly, I opted to go with 17. Give benefit of additional stream-apis, string-utils & test-blocks.
- **Junit 5** - Using Jupiter API for unit testing alongside Jupiter Assertions for calling assert & matchers.

---

### Class Hierarchy & Flow

#### Application.java

Program starts from here. The input file is read from the `resources` folder. Once reading is successful, the read data is structured in `Hierarchy` class for easy calculations later. 

#### Employee Service

Responsible for organising employees under their respective managers & pre-calculate depth of reporting line.

#### Salary Service

Responsible for calculating the requirement of manager's salary deviations from expected ranges. 

### Models & User-defined Types

#### Employee

Stores the data read from the file with 1 additional field called `depth`. This value is calculated later and determines the reporting line upto CEO. 

#### Hierarchy

Additional class to consolidate all employees by their ID & maintain a separate mapping of employees with their respective managers.

### Scope for Optimization

I believe this code will work fine for given type of data in the input.csv. Further improvement scopes can be:

- Using BigDecimal for storing `salary` (makes it easier for storing & calculating fractional amounts accurately)
- Using `parallelStream()` for depth calculation, particularly helpful if the number of records are high in the file.
