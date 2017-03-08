/*
 * Copyright (c) 2017 Uber Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.sparkscript.execution;

import com.uber.sparkscript.antlr4.parsing.ActionStatement;
import com.uber.sparkscript.jdbc.DataSetResult;
import com.uber.sparkscript.util.CredentialProvider;
import com.uber.sparkscript.util.SparkUtils;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintTableActionStatementExecutor implements ActionStatementExecutor {

  public static final String ACTION_NAME = "printTable";

  @Override
  public Object execute(SparkSession sparkSession, ActionStatement actionStatement, CredentialProvider credentialManager) {
    String tableName = actionStatement.getParamValues().get(0).getValue().toString();

    DataSetResult dataSetResult = SparkUtils.getTableData(sparkSession, tableName);
    System.out.println("------------------------------");
    System.out.println("Table: " + tableName);
    dataSetResult.print();
    System.out.println("------------------------------");

    return null;
  }

}
