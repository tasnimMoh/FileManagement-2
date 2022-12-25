package readDB;

import encryption.DecryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class InfoAboutFile {
    public static FileInformation getInfo(FileInformation file, ResultSet result) throws SqlQueryException {
        IEncryptionAndDecryption decryption = new DecryptionFile() ;
        try {
        if (result.next()) {
           file.setName(decryption.encryptAndDecrypt(result.getString("name")));
            file.setType(result.getString("type"));
            file.setContext(result.getBlob("context"));
            file.setVersion(result.getInt("version"));
            file.setSize(result.getString("size"));
            file.setVersionType(result.getInt("versionType"));
        }
    }catch (SQLException e) {
            throw new SqlQueryException("failed to get file data");
        }
        return file;
    }
}
