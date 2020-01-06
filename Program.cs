using CommunicatorLib;
using Config;
using Newtonsoft.Json;
using System;
using System.Collections.Concurrent;
using System.IO;
using System.Threading;
using UCP.Common.DataContracts;

namespace TestApplication
{
    class Program
    {
        static int totalCount = 0;
        //public static BlockingCollection<string> block = new BlockingCollection<string>();
        static void Main(string[] args)
        {            
            totalCount = 0;
            string topicname = "RawData";
            string activemqbrokerurl = "http://localhost:8161";
            Console.WriteLine("Subscriber");
            IMessageBroker messageBroker2 =new ActiveMQBroker("Subscriber62345");
            messageBroker2.Connect();
           
            messageBroker2.MessageReceived += MessageBroker2_MessageReceived;
            messageBroker2.Subscribe(topicname,"Log");

            Thread.Sleep(Timeout.Infinite);
        }
        static void UnhandledExceptionTrapper(object sender, UnhandledExceptionEventArgs e)
        {
            Console.WriteLine(e.ExceptionObject.ToString());
            Console.WriteLine("Press Enter to continue");
            Console.ReadLine();
            Environment.Exit(1);
        }

        private static void MessageBroker2_MessageReceived(object sender, EventArgs e)
        {
            //totalCount = totalCount + 1;
            var mesg = (MessageReceivedEventArgs)e;
            //block.Add(mesg.Body);
            Console.WriteLine(mesg.Body);
           
            //throw new NotImplementedException();
        }












        //Console.WriteLine("Total Count: {0}",totalCount.ToString());
        //ConfigData configData = JsonConvert.DeserializeObject<ConfigData>(mesg.Body);
        ////Console.WriteLine("Ip address: {0}", configData.NetworkSettings.IPAddress);
        //Console.WriteLine("");
        //Console.WriteLine("Network Settings");
        //Console.WriteLine("Proxy Server Address: {0}", configData.NetworkSettings.ProxyIPAddress);
        //Console.WriteLine("Proxy Server Port Number: {0}", configData.NetworkSettings.ProxyPortNumber);
        //Console.WriteLine("Proxy User Name: {0}", configData.NetworkSettings.ProxyUserName);
        //Console.WriteLine("Proxy Password: {0}", configData.NetworkSettings.ProxyPassword);
        //Console.WriteLine("Device Information");
        //Console.WriteLine("Machin Type : {0}", configData.DeviceInformations.MachineType);
        //Console.WriteLine("Machin Family : {0}", configData.DeviceInformations.MachineFamily);
        //Console.WriteLine("Serial Number : {0}", configData.DeviceInformations.SerialNumber);
        //Console.WriteLine("Machine Number : {0}", configData.DeviceInformations.MachineNumber);
        //Console.WriteLine("Machine Model Number : {0}", configData.DeviceInformations.MachineNumber);
        //Console.WriteLine("UCB Information");
        //Console.WriteLine("UCP Serial No : {0}", configData.UCPInformations.UCPSerialNo);
        //Console.WriteLine("UCP Version : {0}", configData.UCPInformations.UCPVersion);
        //Console.WriteLine("Account No : {0}", configData.UCPInformations.UCPSerialNo);
    }
}
