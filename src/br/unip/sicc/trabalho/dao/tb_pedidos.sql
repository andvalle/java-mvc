CREATE TABLE IF NOT EXISTS TB_PEDIDOS( 
            ID int(3) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
            QTDDEITENS int (5) NOT NULL,
            CODIGO varchar(255) NOT NULL,
            TIPO varchar(255) NOT NULL,
           )

INSERT INTO TB_PEDIDOS (ID, QTDDEITENS, CODIGO, TIPO) 
            VALUES (123, 12, 'ABC', 'DINHEIRO');
INSERT INTO TB_PEDIDOS (ID, QTDDEITENS, CODIGO, TIPO) 
            VALUES (132, 10 'ASXxaAA', 'CHEQUE');