SELECT  * FROM beecological.unload;
SELECT  * FROM beecological.bookingRequest;
SELECT  * FROM beecological.wasteUnloaded;
SELECT  * FROM beecological.user;
SELECT  * FROM beecological.center WHERE center.city = "Alatri";

#======= VIEW THE WASTE OF AN UNLOAD ========

SELECT *
FROM beecological.waste
WHERE unload = "4";

#======= VIEW ALL THE UNLOADS AND THEIR WASTE ========

SELECT 	user,owner,date,time,wasteType,wasteQuantity
FROM beecological.unload
	JOIN beecological.waste ON waste.unload = unload.ID