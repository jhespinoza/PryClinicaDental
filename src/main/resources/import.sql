INSERT INTO USUARIO(IDUSUARIO,NOMBRE,CONTRASENIA,HABILITADO,CREADOEN)VALUES(1,'admin','$2a$10$5WUVJTaTvk9GfLA.q7pYLOavF4.Ss4PKbL4DcLUmSfUxMjMJ6/ljG',true,'2019-12-23 15:00:00');
INSERT INTO DOCTOR(idpersona, apellidos, cedula, direccion, nacimiento, nombres, sexo, telefono, especialidad, idusuario) VALUES (1,'Espinoza','1724529035','CASA','1997-12-23 15:00:00','Hernan','M','098309785','Ingeniero',1);

INSERT INTO ROL(IDROL,NOMBRE,IDUSUARIO) VALUES(1,'ROLE_ADMIN',1);
INSERT INTO ROL(IDROL,NOMBRE,IDUSUARIO) VALUES(2,'ROLE_USER',2);