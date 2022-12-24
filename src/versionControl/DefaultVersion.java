package versionControl;

import exceptions.SqlQueryException;
import file.FileInfo;
import readDB.GetFileInfo;
import variables.Variables;
import writeDB.AddFile;
import writeDB.UpdateLastVersion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public class DefaultVersion {
        public static void defaultVersion(Connection connection, String tableName, InputStream inputStream, FileInfo newFile)  {
          try {
               FileInfo previousFile = GetFileInfo.getInfo(connection, newFile);
               newFile.setVersion(previousFile.getVersion() + 1);
               newFile.setVersionType(Variables.DEFAULT_VERSION_TYPE);
               UpdateLastVersion.updateToZero(connection, tableName, previousFile);
               AddFile.addNewFile(connection, tableName, inputStream, newFile);
          } catch (SqlQueryException e) {
                  System.err.println(e.getMessage());
          } catch (IOException e) {
              throw new RuntimeException(e);
          }

        }
}