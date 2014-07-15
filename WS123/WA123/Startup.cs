using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(WA123.Startup))]
namespace WA123
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
