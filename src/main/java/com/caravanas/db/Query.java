package com.caravanas.db;

import com.caravanas.shared.MethodChainingException;

import java.lang.reflect.Field;

public final class Query {
  private enum State {
    DEFAULT,
    INSERTING,
    IN_FETCH
  }

  private Class<?> type;
  private StringBuilder query;
  private String tableName;
  private String idColumnName;
  private String[] columns;
  private State state = State.DEFAULT;

  public Query(Class<?> type) throws Exception {
    this.type = type;

    if (this.type.getAnnotation(Table.class) == null) {
      throw new Exception("Class '" + type.getName() + "' is not persistable");
    }

    this.query = new StringBuilder();

    this.tableName = type.getAnnotation(Table.class).name();

    this.columns = this.getColumns();

    this.idColumnName = getIdColumnName();
  }

  public Query insert() throws Exception {
    if (this.state != State.DEFAULT) {
      throw new MethodChainingException();
    }

    this.query.append("INSERT INTO " + this.tableName + " (");

    for (int index = 0; index < this.columns.length - 1; ++index) {
      this.query.append(this.columns[index] + ", ");
    }

    this.query.append(this.columns[this.columns.length - 1]);

    this.query.append(") VALUES (");

    for (int index = 0; index < this.columns.length - 1; ++index) {
      this.query.append("?, ");
    }

    this.query.append("?)");

    return this;
  }

  public Query select() throws Exception {
    if (this.state != State.DEFAULT) {
      throw new MethodChainingException();
    }

    this.state = State.IN_FETCH;

    this.query.append("SELECT " + idColumnName + ", ");

    for (int index = 0; index < this.columns.length - 1; ++index) {
      this.query.append(this.columns[index] + ", ");
    }

    this.query.append(this.columns[columns.length - 1] + " FROM " + this.tableName);

    return this;
  }

  public Query limit(int limit, int offset) throws Exception {
    if (this.state != State.IN_FETCH) {
      throw new MethodChainingException();
    }

    this.query.append(" LIMIT " + String.valueOf(limit) + " OFFSET " + String.valueOf(offset));

    return this;
  }

  public Query update() throws Exception {
    if (this.state != State.DEFAULT) {
      throw new MethodChainingException();
    }

    this.query.append("UPDATE " + this.tableName + " ");

    for (int index = 0; index < this.columns.length - 1; ++index) {
      this.query.append("SET " + this.columns[index] + " = ? AND ");
    }

    this.query.append(this.columns[this.columns.length - 1] + " = ?");

    return this.where();
  }

  public Query delete() throws Exception {
    if (this.state != State.DEFAULT) {
      throw new MethodChainingException();
    }

    this.query.append("DELETE FROM " + this.tableName);

    return this.where();
  }

  public Query where() throws Exception {
    if (this.state == State.DEFAULT || this.state == State.INSERTING) {
      throw new MethodChainingException();
    }

    this.query.append(" WHERE " + idColumnName + " = ?");

    return this;
  }

  @Override
  public String toString() {
    this.state = State.DEFAULT;

    String sql = this.query.toString();

    System.out.println(sql);

    this.query.setLength(0);

    return sql;
  }

  private String getIdColumnName() throws Exception {
    for (Field field : getClassFields(this.type)) {
      field.setAccessible(true);

      Id id = field.getAnnotation(Id.class);

      if (id == null) {
        continue;
      }

      return id.name().isEmpty() ? field.getName() : id.name();
    }

    return "id";
  }

  private String[] getColumns() throws Exception {
    Field[] fields = getClassFields(this.type);

    String[] attributes = new String[fields.length - 1];

    int index = 0;

    for (Field field : fields) {
      field.setAccessible(true);

      Column column = field.getAnnotation(Column.class);

      if (column == null) {
        continue;
      }

      attributes[index] = column.name().isEmpty() ? field.getName() : column.name();

      ++index;
    }

    return attributes;
  }

  private Field[] getClassFields(Class<?> type) throws Exception {
    if (type.getSuperclass() == null) {
      return type.getDeclaredFields();
    }

    Field[] superclassFields = getClassFields(type.getSuperclass());

    Field[] declaredFields = type.getDeclaredFields();

    Field[] fields = new Field[superclassFields.length + declaredFields.length];

    System.arraycopy(superclassFields, 0, fields, 0, superclassFields.length);

    System.arraycopy(declaredFields, 0, fields, superclassFields.length, declaredFields.length);

    return fields;
  }
}
