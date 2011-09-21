import grails.converters.JSON

class BootStrap {
	
	def init = { servletContext ->
		String VCAP_SERVICES = System.getenv('VCAP_SERVICES')
		println "VCAP_SERVICES: ${System.getenv('VCAP_SERVICES')}n"
  
		try {
		   def servicesMap = JSON.parse(VCAP_SERVICES)
		   servicesMap.each { key, services ->
			  if (key.startsWith('mysql')) {
				 for (service in services) {
					print "MySQL service $service.name: "
					print "url='jdbc:mysql://$service.credentials.hostname:$service.credentials.port/$service.credentials.name', "
					print "user='$service.credentials.user', "
					println "password='$service.credentials.password'n"
				 }
			  }
			  else if (key.startsWith('postgresql')) {
				 for (service in services) {
					print "PostgreSQL service $service.name: "
					print "url='jdbc:postgresql://$service.credentials.hostname:$service.credentials.port/$service.credentials.name', "
					print "user='$service.credentials.user', "
					println "password='$service.credentials.password'n"
				 }
			  }
		   }
		}
		catch (e) {
		   println "Error occurred parsing VCAP_SERVICES: $e.message"
		}
	 }
	
    def destroy = {
    }
}
