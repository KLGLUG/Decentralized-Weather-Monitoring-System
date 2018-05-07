# import mysql module
import MySQLdb
import datetime
import time
# import regular expression module
import re

original_fyle = open('/home/sowmya/sample1.txt', 'r')

# initialize & establish connection 
con = MySQLdb.connect(host="localhost",user="root", passwd="root",db="airmonitor") 
cur = con.cursor()

# prepare your ready file 
while(True):
	#time.sleep(30)
	line=original_fyle.readline()
	location=line[7:10]
	a=line.find('humidity')
	humidity=line[a+9:a+13]
	a=line.find('smoke')
	smoke=line[a+6:a+9]
	a=line.find('temperature')
	temperature=line[a+12:a+15]
	ts = time.time()
	date=str(ts)
	sql="INSERT INTO monitor VALUES('%s','%s','%s','%s','%s')" %(location,temperature,humidity,smoke,date)
	cur.execute(sql)
	con.commit()
	i=10000000
	while(i>0):
		i=i-1
	
original_fyle.close();


