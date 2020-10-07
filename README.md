description
------------------------
A restaurant management project, shop owner could manage the shop landing page while 
customers could order food in the page.  
For owners, when they log in, they could see landing page
listing all their shops.  
For customers, they could check all shops in restaurants' platform page without logging in.
When they click the restaurant name, they will be redirected to the landing page,
where show all details of the restaurant such as head, feature and menu. 

pages
-----------------------
1. login page
2. registration page
3. profile page: update the profile
4. landing page: when shop owner login, it could display
all shops each of which contain three buttons.
5. edit head page
6. edit feature page
7. edit menu page
8. platform page, listing all restaurants names
9. landing pages.

API
------------------------
1. /register  
check if the username has already existed. if not, create
a new user (shop owner)
2. /login  
check if the username has already existed, if so, login and 
return a token stored in the localStorage.
3. /profile  
show all infomation of the user
4. CRUD

table
------------------------
...

