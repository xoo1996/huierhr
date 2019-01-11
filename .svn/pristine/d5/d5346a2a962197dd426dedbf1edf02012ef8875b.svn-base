/**
 * ServiceFacadeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.radf.webservice.client;

public class ServiceFacadeServiceLocator extends org.apache.axis.client.Service implements org.radf.webservice.client.ServiceFacadeService {

    // Use to get a proxy class for SendService
    private final java.lang.String SendService_address = LogonURL.getUrl();

    public java.lang.String getSendServiceAddress() {
        return SendService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SendServiceWSDDServiceName = "SendService";

    public java.lang.String getSendServiceWSDDServiceName() {
        return SendServiceWSDDServiceName;
    }

    public void setSendServiceWSDDServiceName(java.lang.String name) {
        SendServiceWSDDServiceName = name;
    }

    public org.radf.webservice.client.ServiceFacade getSendService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SendService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSendService(endpoint);
    }

    public org.radf.webservice.client.ServiceFacade getSendService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.radf.webservice.client.SendServiceSoapBindingStub _stub = new org.radf.webservice.client.SendServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSendServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.radf.webservice.client.ServiceFacade.class.isAssignableFrom(serviceEndpointInterface)) {
                org.radf.webservice.client.SendServiceSoapBindingStub _stub = new org.radf.webservice.client.SendServiceSoapBindingStub(new java.net.URL(SendService_address), this);
                _stub.setPortName(getSendServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("SendService".equals(inputPortName)) {
            return getSendService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost:8080/plat/services/SendService", "ServiceFacadeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("SendService"));
        }
        return ports.iterator();
    }

}
