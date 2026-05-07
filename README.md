
Group ID-SE Group Number – 1
Topic Name-Hotel Management System for Weddings
	Student Registration Number	Student Name
	IT20089054	Deshan S.N.D.N
	IT21025358	Dias N.T.G.P
	IT20660734	k.Pragas
	IT20652432	Rajekiran .R
 Group Members 

 IT20660734_k. Pragas.K -HALL RESERVATION TEST CASES

Test Case ID	Test Scenario	Test Steps	Expected Result	Status
TC_HR_01	User books hall with valid details	Enter valid booking details and click Save	Booking should be saved successfully	Pass
TC_HR_02	Booking with empty customer name	Leave customer name empty and submit	System should show validation message	Pass
TC_HR_03	Booking with empty hall name	Leave hall name empty	System should display error message	Pass
TC_HR_04	Booking with past date	Select a previous date	System should reject booking	Pass
TC_HR_05	Booking with guest count = 0	Enter guest count as 0	System should show validation error	Pass
TC_HR_06	Duplicate booking for same hall/date	Book same hall on same date twice	Book same hall on same date twice	Pass
TC_HR_07	Update existing booking	Edit booking details and click Update	Booking should update successfully	Pass
TC_HR_08	Delete booking	Click Delete button	Booking should be removed from system	Pass
TC_HR_09	View all bookings	Open dashboard booking table	System should display all bookings	Pass
TC_HR_10	Booking without login	Try accessing booking page without login	System should redirect to login page	Pass

