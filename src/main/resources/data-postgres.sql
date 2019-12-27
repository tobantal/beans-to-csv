DO
$do$
BEGIN 
   FOR i IN 1..1000000 LOOP
            insert into users (field1, field2, field3, field4, field5,
                  field6, field7, field8, field9, field10,
                  field11, field12, field13, field14, field15) values 
                  (md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text),
                  md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text),
                   md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text), md5(random()::text)
                  );
   END LOOP;
END
$do$;