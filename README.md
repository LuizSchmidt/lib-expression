# edge-lib-expression

## Compararison Types

name = 'John'
age = 18
fruits = ['pear', 'apple', 'banana']
married = true 

### Equals and Not Equals

Tokens: (=) and (!=)
Rule: Any type

- name = 'John' => TRUE
- name = 'Carl' => FALSE
- name != 'Mary' => TRUE
- name = 18 => FALSE

- age = 18 => TRUE
- age = '18' => FALSE
- age = 18.0 => TRUE
- age != 20 => TRUE

- fruits = ['pear', 'apple', 'banana'] => TRUE
- fruits = ['pear', 'apple'] => FALSE
- fruits != ['pear', 'apple'] => TRUE

### Contains and Not Contains

Tokens: IN, NOT IN

- fruits IN ['pear', 'apple', 'banana', 'orange'] => TRUE
- 'apple' IN fruits => TRUE
- 'orange' NOT IN ['pear', 'apple'] => TRUE

### Use static boolean

Rule: True or False

- true => TRUE
- false => FALSE

### GT, LT, GTE, LTE

Tokens: (>=) and (<=) and (>) and (<)

- age > 18 => FALSE
- age >= 18 => TRUE
- age < 18 => FALSE
- age <= 18 => TRUE

## Types

* Literal
* boolean
* Numeric
* List
* Identifier