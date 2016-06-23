package Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonTransformer {

	@SuppressWarnings("unchecked")
	public static void main(String arsgs[]) throws Exception {
		long startTime = System.currentTimeMillis();
		JsonTransformer jsonTransformer = new JsonTransformer();
		String jsonString = "{\"_id\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"version\" : \"1549f09bdf5d3de0aab2a2d773b6506ed8e3f24e\",\"events\" : [{\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxWorkspaceCreatedEvent\",\"client\" : {\"clientId\" : \"612bf3d8-48c2-4bf5-b01f-e77a2711c6b8\",\"firstName\" : \"LNAME112031323\",\"lastName\" : \"FNAME112031323\",\"taxIdentificationNumber\" : \"112031323\",\"dateOfBirth\" : {\"$date\" : \"1983-04-04T06:00:00.000Z\"}},\"taxYear\" : {\"$numberLong\" : \"2013\"},\"type\" : \"CONSUMER\",\"eid\" : \"30d15575-d6af-4268-89f0-ada9fd43f74f\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.040Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"97525a82b17be891f70a32bbc127e0d3f429bcb5\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.ConsumerTaxReturnCreatedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"practice\" : false,\"taxProductType\" : \"1040\",\"eid\" : \"2570431e-4e82-4de0-8004-66893446364a\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.041Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"97525a82b17be891f70a32bbc127e0d3f429bcb5\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxpayerUpdatedOnConsumerTaxReturnEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"taxpayer\" : {\"taxIdentificationNumber\" : \"112031323\",\"firstName\" : \"LNAME112031323\",\"lastName\" : \"FNAME112031323\",\"dateOfBirth\" : {\"$date\" : \"1983-04-04T06:00:00.000Z\"}},\"cardinality\" : \"PRIMARY\",\"eid\" : \"a8ebf89d-5372-4f1e-9efc-bf3d1d2c2add\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.041Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"97525a82b17be891f70a32bbc127e0d3f429bcb5\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.FilingStatusOnConsumerTaxReturnChangedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"filingStatus\" : \"SINGLE\",\"eid\" : \"9259eea8-f337-4754-b896-c9bc3006ea0a\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.042Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"97525a82b17be891f70a32bbc127e0d3f429bcb5\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.SubscriberUpdatedEvent\",\"subscriber\" : {\"subscriberId\" : \"40800\",\"subscriberType\" : \"OWNER\"},\"eid\" : \"b6df7812-5b02-4ac6-9f2d-c32c3342068e\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.042Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"97525a82b17be891f70a32bbc127e0d3f429bcb5\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxDataResourcesStoredEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"taxDataResource\" : {\"name\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda.clz\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/r/c85d707c-e729-43d6-b56a-4bdf2a204dda/d/c85d707c-e729-43d6-b56a-4bdf2a204dda.clz?rev=efea1d1bbb3b018ea57fc9f13c69ded5c9343e88\",\"contentType\" : \"x-hrb-application/clz\",\"metadata\" : {\"x-hrb-content-version\" : \"1\",\"x-hrb-content-app\" : \"BW\"}},\"eid\" : \"b71e3771-b707-40cb-8ee7-1291d4d78e82\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.418Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"d789d0c069a409fb63e829b15c3d26b4abe0fa05\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.PaymentAddedEvent\",\"payment\" : {\"id\" : \"75dc0e0d-da25-4325-ba23-87b32baf68db\",\"resourceLink\" : {\"name\" : \"75dc0e0d-da25-4325-ba23-87b32baf68db.xml\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/p/75dc0e0d-da25-4325-ba23-87b32baf68db.xml?rev=cafc626e31765511c14aec0b76dc691b00f96c02\",\"contentType\" : \"x-hrb-application/pos\",\"metadata\" : {\"x-hrb-content-version\" : \"01\",\"x-hrb-content-app\" : \"BW\"}},\"lineItems\" : [{\"returnId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"paymentStatus\" : \"PAID\",\"balanceDue\" : 200.0}],\"createdDateTime\" : {\"$date\" : \"2013-12-03T23:00:55.723Z\"}},\"eid\" : \"8d2cac9a-ecd7-42fa-89ea-234e4834d25c\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:55.733Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"46b801ba28865580cf4762d90c1039211bb006db\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxReturnEntityCreatedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"filingAgency\" : \"US\",\"entityGuid\" : \"GUID112031323\",\"eid\" : \"9fe12792-cb59-4a64-bc3c-ca730c60a619\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:56.221Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"280c35ae5d59dc200d23332a3366f7541706f386\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxReturnEntityCreatedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"filingAgency\" : \"MO\",\"entityGuid\" : \"GUID112031323\",\"eid\" : \"a4f2b738-62f0-4a5a-9a4e-a7dcce2ab054\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:56.729Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"cd0a51f3e79956623d0cdab3775b843b34f2594c\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxReturnResourceAddedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"agency\" : \"MO\",\"resourceLink\" : {\"name\" : \"abcdeee.pdf\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/r/c85d707c-e729-43d6-b56a-4bdf2a204dda/e/MO/f/abcdeee.pdf?rev=0b8788f697fc929a56a8d7d54d87f95b47200630\",\"contentType\" : \"application/pdf\",\"metadata\" : {\"x-hrb-content-version\" : \"1\"}},\"eid\" : \"5c93487c-e354-4b3d-9aff-71e996bcdbf8\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.259Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"ec9215796429516e89284faa070d5f112230b609\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxReturnResourceAddedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"agency\" : \"US\",\"resourceLink\" : {\"name\" : \"abcdeee.pdf\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/r/c85d707c-e729-43d6-b56a-4bdf2a204dda/e/US/f/abcdeee.pdf?rev=0b8788f697fc929a56a8d7d54d87f95b47200630\",\"contentType\" : \"application/pdf\",\"metadata\" : {\"x-hrb-content-version\" : \"1\"}},\"eid\" : \"824fee84-c25d-465f-b9e3-9a9370c68c06\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.662Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"bbe6e01f81e6e4bcbebe3951352b37921289b4cb\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.FilingCreatedEvent\",\"filingId\" : \"03d77832-e782-4b7f-b2d1-9c1d072caf66\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"env\" : \"TS13_BANK\",\"eid\" : \"4c93d1db-1e08-4e7c-b9e0-ba61d92fa381\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.989Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"1549f09bdf5d3de0aab2a2d773b6506ed8e3f24e\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.FilingEntityCreatedEvent\",\"filingId\" : \"03d77832-e782-4b7f-b2d1-9c1d072caf66\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"returnFiling\" : {\"agency\" : \"US\",\"methodType\" : \"PAPER\",\"amendment\" : true,\"extension\" : false},\"eid\" : \"498cc31e-7b21-4c79-bd49-7bc565026f08\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.990Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"1549f09bdf5d3de0aab2a2d773b6506ed8e3f24e\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.FilingEntityCreatedEvent\",\"filingId\" : \"03d77832-e782-4b7f-b2d1-9c1d072caf66\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"returnFiling\" : {\"agency\" : \"MO\",\"methodType\" : \"PAPER\",\"amendment\" : true,\"extension\" : false},\"eid\" : \"b61604fb-4d97-46c8-b6ab-096d7346ea82\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.990Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"1549f09bdf5d3de0aab2a2d773b6506ed8e3f24e\",\"ep\" : {}}, {\"et\" : \"com.hrb.esg.ods.domain.tax.event.TaxReturnActivityCreatedEvent\",\"returnFileId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"activity\" : {\"t\" : \"com.hrb.esg.ods.domain.tax.activity.FilingActivity\",\"id\" : \"eaa5d0fe-5b50-4e7f-9f73-40be6b5a193d\",\"created\" : {\"$date\" : \"2013-12-03T23:00:57.990Z\"},\"officeId\" : \"40800\",\"taxYear\" : {\"$numberLong\" : \"2013\"},\"returnType\" : \"1040\",\"taxReturnFile\" : {\"name\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda.clz\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/r/c85d707c-e729-43d6-b56a-4bdf2a204dda/d/c85d707c-e729-43d6-b56a-4bdf2a204dda.clz?rev=efea1d1bbb3b018ea57fc9f13c69ded5c9343e88\",\"contentType\" : \"x-hrb-application/clz\",\"metadata\" : {\"x-hrb-content-version\" : \"1\",\"x-hrb-content-app\" : \"BW\"}},\"payment\" : {\"id\" : \"75dc0e0d-da25-4325-ba23-87b32baf68db\",\"resourceLink\" : {\"name\" : \"75dc0e0d-da25-4325-ba23-87b32baf68db.xml\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/p/75dc0e0d-da25-4325-ba23-87b32baf68db.xml?rev=cafc626e31765511c14aec0b76dc691b00f96c02\",\"contentType\" : \"x-hrb-application/pos\",\"metadata\" : {\"x-hrb-content-version\" : \"01\",\"x-hrb-content-app\" : \"BW\"}},\"lineItems\" : [{\"returnId\" : \"c85d707c-e729-43d6-b56a-4bdf2a204dda\",\"paymentStatus\" : \"PAID\",\"balanceDue\" : 200.0}],\"createdDateTime\" : {\"$date\" : \"2013-12-03T23:00:55.723Z\"}},\"filing\" : {\"t\" : \"com.hrb.esg.ods.domain.tax.Filing\",\"resources\" : [],\"filingId\" : \"03d77832-e782-4b7f-b2d1-9c1d072caf66\",\"entities\" : {\"GUID112031323\" : {\"t\" : \"com.hrb.esg.ods.domain.tax.FilingEntity\",\"resources\" : [{\"name\" : \"abcdeee.pdf\",\"uriPath\" : \"/00251df5-c866-4ad6-ba03-f871d71a84b2/r/c85d707c-e729-43d6-b56a-4bdf2a204dda/e/MO/f/abcdeee.pdf?rev=0b8788f697fc929a56a8d7d54d87f95b47200630\",\"contentType\" : \"application/pdf\",\"metadata\" : {\"x-hrb-content-version\" : \"1\"}}],\"agency\" : \"MO\",\"guid\" : \"GUID112031323\",\"creationDate\" : {\"$date\" : \"2013-12-03T23:00:57.990Z\"},\"method\" : \"PAPER\",\"filingStatus\" : \"PENDING_TRANSMISSION\",\"amendment\" : true,\"extension\" : false}},\"filingTime\" : {\"$date\" : \"2013-12-03T23:00:57.989Z\"},\"status\" : \"CREATED\",\"env\" : \"TS13_BANK\"}},\"eid\" : \"fedfe106-1a24-4f48-923e-e7349e553354\",\"ets\" : {\"$date\" : \"2013-12-03T23:00:57.990Z\"},\"aid\" : \"00251df5-c866-4ad6-ba03-f871d71a84b2_u\",\"ev\" : \"1549f09bdf5d3de0aab2a2d773b6506ed8e3f24e\",\"ep\" : {}}]}";
		JSONObject json = jsonTransformer.toJson(jsonString);
		iterateMap(null, jsonToMap(json));
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time startTime : " + startTime);
		System.out.println("Time endTime : " + endTime);
		System.out.println("Time Taken : " + totalTime);
		System.out.println("Time Taken Toal : " + getDurationBreakdown(totalTime));

	}

	public static void main13(String arsgs[]) throws Exception {
		JsonTransformer jsonTransformer = new JsonTransformer();
		String jsonString = "{\"_id\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43_u\",\"version\": \"2d307029d34bd4cd4f9535247d1ca55f3634dcc4\",\"events\": [{\"taxYear\": 2015,\"actor\": {\"t\": \"unknown\",\"name\": \"upgrade\",\"officeId\" : \"87971\",\"ptin\" : \"P123456789\"},\"practice\": false,\"workspaceType\": \"CONSUMER\",\"channel\": \"HRBO\",\"client\": {\"clientId\": \"1dbc19b3-f535-486c-aa18-9fde011cea2f\",\"firstName\": \"Test\",\"lastName\": \"DBWODS\",\"taxIdentificationNumber\": \"238749187\",\"dateOfBirth\": {\"$date\": \"1990-01-01T06:00:00.000Z\"}},\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"taxProductType\": \"1040\",\"eid\": \"01080a4c-4673-4cd1-8b78-5d605a73824b\",\"ets\": {\"$date\": \"2016-04-11T16:36:18.785Z\"},\"aid\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43_u\",\"ev\": \"efd54f9e71a86f1627c45c75e32c77b44e6f18a3\",\"ep\": {},\"et\": \"com.hrb.esg.ods.domain.tax.event.v2.TaxWorkspaceCreatedEvent\",\"taxpayer\": {\"taxIdentificationNumber\": \"238749187\",\"firstName\": \"Test\",\"lastName\": \"DBWODS\",\"dateOfBirth\": {\"$date\": \"1990-01-01T06:00:00.000Z\"}},\"cardinality\": \"PRIMARY\",\"filingStatus\": \"SINGLE\",\"original\": {},\"modified\": {\"return_dot_prepBeginDate\": \"2016-04-11T11:36:18.786\",\"return_dot_id\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"return_dot_aol_dot_status\": \"\",\"return_dot_taxSystemType\": \"1040\",\"return_dot_lastEditDate\": \"2016-04-11T11:36:18.786\",\"return_dot_workspace_dot_type\": \"CONSUMER\",\"taxpayer_dot_name_dot_first\": \"Test\",\"taxpayer_dot_dob\": \"1990-01-01\",\"taxpayer_dot_name_dot_last\": \"DBWODS\",\"return_dot_taxYear\": \"2015\",\"return_dot_workspace_dot_id\": \"05f4ee66-4958-48e7-9dc3-031a194d6b43\",\"taxpayer_dot_ssn\": \"238749187\",\"return_dot_amended\": \"false\",\"return_dot_segment2\": \"false\",\"return_dot_extension\": \"false\"},\"taxDataResource\": {\"name\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/d/81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz?rev=01f5d734a7f10ccc61faf2bda59bc5b7a3271a7a\",\"contentType\": \"x-hrb-application/clz\",\"metadata\": {\"x-hrb-audit-id\": \"3986e888-838b-4dee-9aef-db053418b7b9\"}},\"filingAgency\": \"US\",\"entityGuid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"payment\": {\"id\": \"b580b3ae-cd3c-4efc-a282-44c1da677304\",\"resourceLink\": {\"name\": \"b580b3ae-cd3c-4efc-a282-44c1da677304.xml\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/p/b580b3ae-cd3c-4efc-a282-44c1da677304.xml?rev=6f6f25f3a9bb1e84d782673e17f7739f64e982bb\",\"contentType\": \"x-hrb-application/pos\",\"metadata\": {\"x-hrb-office-id\": \"87971\",\"x-hrb-audit-id\": \"063eb0eb-d89e-4b6a-a962-cb0b8a939fcb\",\"x-hrb-payment-status\": \"PAID\",\"x-hrb-guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"x-hrb-content-version\": \"3\",\"x-hrb-payment-balancedue\": \"200\"}},\"lineItems\": [{\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"paymentStatus\": \"PAID\",\"balanceDue\": 200.0}],\"createdDateTime\": {\"$date\": \"2016-04-11T16:36:20.070Z\"}},\"subscriber\": {\"subscriberId\": \"87971\",\"subscriberType\": \"OWNER\"},\"filingId\": \"40c509f4-3dea-4521-b0a8-83d48dcd352e\",\"env\": \"TS15_DIGQA\",\"returnFiling\": {\"agency\": \"US\",\"methodType\": \"ELECTRONIC\",\"amendment\": false,\"extension\": false},\"activity\": {\"t\": \"com.hrb.esg.ods.domain.tax.activity.FilingActivity\",\"id\": \"9bfcf676-2543-4d5b-9a61-4a830a2cfd7b\",\"created\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"officeId\": \"87971\",\"taxYear\": 2015,\"workspaceType\" : \"CONSUMER\",\"channel\" : \"HRBO\",\"returnType\": \"1040\",\"taxReturnFile\": {\"name\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/d/81463728-c1a5-46a5-9acc-4a73b5e1b7f3.clz?rev=01f5d734a7f10ccc61faf2bda59bc5b7a3271a7a\",\"contentType\": \"x-hrb-application/clz\",\"metadata\": {\"x-hrb-audit-id\": \"3986e888-838b-4dee-9aef-db053418b7b9\"}},\"payment\": {\"id\": \"b580b3ae-cd3c-4efc-a282-44c1da677304\",\"resourceLink\": {\"name\": \"b580b3ae-cd3c-4efc-a282-44c1da677304.xml\",\"uriPath\": \"/05f4ee66-4958-48e7-9dc3-031a194d6b43/r/81463728-c1a5-46a5-9acc-4a73b5e1b7f3/p/b580b3ae-cd3c-4efc-a282-44c1da677304.xml?rev=6f6f25f3a9bb1e84d782673e17f7739f64e982bb\",\"contentType\": \"x-hrb-application/pos\",\"metadata\": {\"x-hrb-office-id\": \"87971\",\"x-hrb-audit-id\": \"063eb0eb-d89e-4b6a-a962-cb0b8a939fcb\",\"x-hrb-payment-status\": \"PAID\",\"x-hrb-guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"x-hrb-content-version\": \"3\",\"x-hrb-payment-balancedue\": \"200\"}},\"lineItems\": [{\"returnId\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"paymentStatus\": \"PAID\",\"balanceDue\": 200.0}],\"createdDateTime\": {\"$date\": \"2016-04-11T16:36:20.070Z\"}},\"filing\": {\"resources\": [],\"filingId\": \"40c509f4-3dea-4521-b0a8-83d48dcd352e\",\"entities\": {\"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\": {\"resources\": [],\"agency\": \"US\",\"guid\": \"81463728-c1a5-46a5-9acc-4a73b5e1b7f3\",\"creationDate\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"method\": \"ELECTRONIC\",\"filingStatus\": \"PENDING_TRANSMISSION\",\"amendment\": false,\"extension\": false,\"t\": \"com.hrb.esg.ods.domain.tax.FilingEntity\"}},\"filingTime\": {\"$date\": \"2016-04-11T16:36:20.472Z\"},\"status\": \"CREATED\",\"env\": \"TS15_DIGQA\",\"t\": \"com.hrb.esg.ods.domain.tax.Filing\"},\"attachmentRequests\": []}}]}";
		System.out.println("String to Json : " + jsonTransformer.toJson(jsonString).get("_id"));

		JSONObject jsonObject = new JSONObject();
		String sampleString = "SampleString";
		Map<String, Object> sampleMap = new HashMap<String, Object>();
		sampleMap.put("Hi", "Hello");
		sampleMap.put("bye", "cu ");
		List<String> sampleList = new ArrayList<String>();
		Map<String, Object> sampleNullMap = new HashMap<String, Object>();
		sampleNullMap.put("1", "one");
		sampleNullMap.put("2", "teo");
		List<Map<String, Object>> complexList = new ArrayList<Map<String, Object>>();
		sampleList.add("one");
		sampleList.add("two");
		complexList.add(sampleNullMap);
		complexList.add(sampleMap);
		sampleList.addAll(sampleList);

		jsonObject.put("string", sampleString);
		jsonObject.put("map", sampleMap);
		jsonObject.put("list", complexList);

		System.out.println("Json to String : " + jsonTransformer.fromJson(jsonObject));
		iterateMap("jsonObject", jsonToMap(jsonObject));
	}


	@SuppressWarnings("unchecked")
	public static void iterateMap(String key, Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String newKey = null == key ? entry.getKey() : key + "_" + entry.getKey();
			if (entry.getValue() instanceof Map) {
				iterateMap(newKey, (Map<String, Object>) entry.getValue());
			} else if (entry.getValue() instanceof List) {
				iterateList(newKey, (List<Object>) entry.getValue());
			} else {
				System.out.println(newKey + " : " + entry.getValue());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static int iterateList(String key, List<Object> list) {
		int i = 0;
		for (Object obj : list) {
			String newKey = (String) (null == key ? i++ : key + "_" + i++);
			if (obj instanceof List) {
				iterateList(newKey, (List<Object>) obj);
			} else if (obj instanceof Map) {
				iterateMap(newKey, (Map<String, Object>) obj);
			} else {
				System.out.println(newKey + " : " + obj);
			}
		}
		return i;
	}

	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if (json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	private String fromJson(Object obj) {
		String string = null;
		if (obj instanceof JSONObject) {
			JSONObject jsonObj = (JSONObject) obj;
			string = jsonObj.toString();
		}
		return string;

	}

	private JSONObject toJson(String string) throws Exception {
		JSONObject jsonObj = new JSONObject(string);
		return jsonObj;
	}

	public static String getDurationBreakdown(long millis) {
		if (millis < 0) {
			throw new IllegalArgumentException("Duration must be greater than zero!");
		}

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		millis -= TimeUnit.SECONDS.toMillis(seconds);

		StringBuilder sb = new StringBuilder(64);
		if (days > 0) {
			sb.append(days);
			sb.append(" Days ");
		}
		if (hours > 0) {
			sb.append(hours);
			sb.append(" Hours ");
		}
		if (minutes > 0) {
			sb.append(minutes);
			sb.append(" Minutes ");
		}
		if (seconds > 0) {
			sb.append(seconds);
			sb.append(" Seconds ");
		}
		if (millis > 0) {
			sb.append(millis);
			sb.append(" Milli Seconds");
		}
		// System.out.println(sb.toString());
		return (sb.toString());
	}
}
