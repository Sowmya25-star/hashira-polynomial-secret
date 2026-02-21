# Polynomial Secret Reconstruction (Hashira Assignment)

## Problem

Recover the constant term `c` of a polynomial using minimum k roots provided in encoded JSON format.

## Approach

* Read JSON input
* Decode values from various bases using BigInteger
* Extract first k coordinate points
* Apply Lagrange Interpolation
* Compute f(0) = constant term (secret)

## Technologies

Java, BigInteger, BigDecimal, JSON parsing

## Output

Secret (c) = -6290016743746469796
