package com.kaltura.client.test.utils;

public class KsqlBuilder {
    private String query;
    private StringBuilder sb;

    public KsqlBuilder() {
        sb = new StringBuilder();
    }

    // numerical fields operators
    public KsqlBuilder equal(String key, int value) {
        sb.append(key).append("=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder notEqual(String key, int value) {
        sb.append(key).append("!=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder greater(String key, int value) {
        sb.append(key).append(">").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder less(String key, int value) {
        sb.append(key).append("<").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder greaterOrEqual(String key, int value) {
        sb.append(key).append(">=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder lessOrEqual(String key, int value) {
        sb.append(key).append("<=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    // alpha-numerical fields
    public KsqlBuilder equal(String key, String value) {
        sb.append(key).append("=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder notEqual(String key, String value) {
        sb.append(key).append("!=").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder like(String key, String value) {
        sb.append(key).append("~").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder notLike(String key, String value) {
        sb.append(key).append("!~").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    public KsqlBuilder startsWith(String key, String value) {
        sb.append(key).append("^").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    // searching one value in a list of numeric values operator
    public KsqlBuilder inList(String key, String value) {
        sb.append(key).append(":").append("'").append(String.valueOf(value)).append("' ");
        return this;
    }

    // exists operators
    public KsqlBuilder exists(String key) {
        sb.append(key).append("+''");
        return this;
    }

    public KsqlBuilder notExists(String key) {
        sb.append(key).append("!+''");
        return this;
    }

    // logical conjunctions
    public KsqlBuilder and(String statment) {
        sb.append("(and ").append(statment).append(") ");
        return this;
    }

    public KsqlBuilder or(String statment) {
        sb.append("(or ").append(statment).append(") ");
        return this;
    }

    public KsqlBuilder openAnd() {
        sb.append("(and ");
        return this;
    }

    public KsqlBuilder openOr() {
        sb.append("(or ");
        return this;
    }

    public KsqlBuilder closeAnd() {
        String s = sb.toString().trim();
        sb = new StringBuilder(s).append(") ");
        return this;
    }

    public KsqlBuilder closeOr() {
        String s = sb.toString().trim();
        sb = new StringBuilder(s).append(") ");
        return this;
    }

    public String toString() {
        return sb.toString().trim();
    }
}
