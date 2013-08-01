


@TypeDefs( {
  @TypeDef(name = "encryptedString",
      typeClass = EncryptedStringType.class,
      parameters = {
        @Parameter(name = "encryptorRegisteredName",
            value = "strongHibernateStringEncryptor")
      })
})

package org.pt.sports.SportsWebApp.model;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedStringType;